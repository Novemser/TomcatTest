package cases.novemser.el.conversion;

import cases.TestResource;
import org.apache.el.lang.ELSupport;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static cases.TestResource.logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.conversion
 * Author:  Novemser
 * 2017/5/30
 */
public class TestConversion {
    /**
     * 空字符串+1
     * 根据expression_language-2_2-mrel-spec.pdf:
     * If A is null or "", return 0.
     * 所以抛出异常将会是一个BUG
     *
     * @throws Exception
     */
    @Test
    public void test0010() throws Exception {
        TestResource.testExpression("DN-21-0034", "${\"\"+1}", "1");
    }

    @Test
    public void test0011() throws Exception {
        TestResource.testExpression("DN-21-0035", "${\"\"+2.2}", "2.2");
    }

    @Test
    public void test0012() throws Exception {
        TestResource.testExpression("DN-21-0036", "${null+1}", "1");
    }

    @Test
    public void test0013() throws Exception {
        TestResource.testExpression("DN-21-0037", "${null+2.2}", "2.2");
    }

    @Test
    public void test0014() throws Exception {
        TestResource.testExpression("DN-21-0038", "${null+null}", "0");
    }

    @Test
    public void test0015() throws Exception {
        TestResource.testExpression("DN-21-0039", "${\"\"+\"\"}", "0");
    }

    @Test
    public void test0016() throws Exception {
        TestResource.testExpression("DN-21-0040", "${\"\"+null}", "0");
    }

    @Test
    public void testCoerceAToString() throws Exception {
        testStringEquals("DN-21-0041", "str", "str");
        testStringEquals("DN-21-0042", "", null);
        testStringEquals("DN-21-0043", CLASS_EN.SLOW.name(), CLASS_EN.SLOW);
        testStringEquals("DN-21-0044", toString(), this);
    }

    /**
     * 空字符串
     * 根据expression_language-2_2-mrel-spec.pdf:
     * If A is null or "", return 0.
     * 所以抛出异常将会是一个BUG
     *
     * @throws Exception
     */
    @Test
    public void testCoerceAToNumber() throws Exception {
        testNumberEquals("DN-21-0045", 0, "");
        testNumberEquals("DN-21-0046", 0L, null);

        Character character = 'H';
        // If A is Character, convert A to new Short((short)a.charValue())
        // BUG:并没有把character转换成short，而是当成string处理了
        // Note:已经在高版本修复了
        testNumberEquals("DN-21-0047", new Short(((short) character.charValue())), character);

        // If A is Boolean, then error.
        try {
            Boolean b = false;
            ELSupport.coerceToNumber(b);
            logger.error("Exception was not thrown in DN-21-0048:If A is Boolean, then error.");
            fail();
        } catch (Throwable ignored) {
            logger.info("DN-21-0048 passed.");
        }

        testNumberEquals("DN-21-0049", 11, 11);

        BigDecimal bd = new BigDecimal(22.32112333333333333312312379861286787612387561238573128);
        Integer ii = 23;
        testNumberEquals("DN-21-0050", bd.toBigInteger(), bd, BigInteger.class);
        testNumberEquals("DN-21-0051", BigInteger.valueOf(ii.longValue()), ii, BigInteger.class);
        BigInteger bi = new BigInteger("798312798312987123987123981237987123987132987123");
        testNumberEquals("DN-21-0052", new BigDecimal(bi), bi, BigDecimal.class);
        testNumberEquals("DN-21-0053", new BigDecimal(ii.doubleValue()), ii, BigDecimal.class);

        String A = "2314214211239009909009123098132132";
        testNumberEquals("DN-21-0054", new BigDecimal(A), A, BigDecimal.class);

        try {
            A = "asdijo";
            ELSupport.coerceToNumber(A, BigDecimal.class);
            logger.error("Exception was not thrown in DN-21-0055:If new BigDecimal(A) throws an exception then error.");
            fail();
        } catch (Throwable e) {
            logger.info("DN-21-0055 passed.");
        }

        A = "312312123123123312231123312123";
        testNumberEquals("DN-21-0056", new BigInteger(A), A, BigInteger.class);

        try {
            A = "asdijo2";
            ELSupport.coerceToNumber(A, BigInteger.class);
            logger.error("Exception was not thrown in DN-21-0057:If new BigDecimal(A) throws an exception then error.");
            fail();
        } catch (Throwable e) {
            logger.info("DN-21-0057 passed.");
        }
    }

    @Test
    public void testCoerceToCharacter() throws Exception {
        testCharEquals("DN-21-0058", (char)0, "");
        testCharEquals("DN-21-0059", (char)0, null);
        // If A is Boolean, then error.
        try {
            Boolean b = false;
            ELSupport.coerceToCharacter(b);
            logger.error("Exception was not thrown in DN-21-0060:If A is Boolean, then error.");
            fail();
        } catch (Throwable ignored) {
            logger.info("DN-21-0060 passed.");
        }

        testCharEquals("DN-21-0061", 'H', 72);
        testCharEquals("DN-21-0062", 'S', "String");
        testCharEquals("DN-21-0063", 'n', "null");
    }

    @Test
    public void testCoerceToBoolean() throws Exception {
        testBooleanEquals("DN-21-0064", false, "");
        testBooleanEquals("DN-21-0065", false, null);
        Boolean A = false;
        testBooleanEquals("DN-21-0066", A, A);
        testBooleanEquals("DN-21-0067", true, "true");
        testBooleanEquals("DN-21-0068", true, "True");
        testBooleanEquals("DN-21-0069", false, "2333");
        testBooleanEquals("DN-21-0070", false, "T");
    }

    public void testBooleanEquals(String passTestCase, Boolean result, Object coerce) {
        try {
            assertEquals(result, ELSupport.coerceToBoolean(coerce));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testCharEquals(String passTestCase, Character result, Object coerce) {
        try {
            assertEquals(result, ELSupport.coerceToCharacter(coerce));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testStringEquals(String passTestCase, String result, Object coerce) {
        try {
            assertEquals(result, ELSupport.coerceToString(coerce));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testNumberEquals(String passTestCase, Number result, Object coerce) {
        try {
            assertEquals(result, ELSupport.coerceToNumber(coerce));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testNumberEquals(String passTestCase, Number result, Object coerce, Class c) {
        try {
            assertEquals(result, ELSupport.coerceToNumber(coerce, c));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    enum CLASS_EN {
        SLOW, FAST, DESPERATE
    }
}
