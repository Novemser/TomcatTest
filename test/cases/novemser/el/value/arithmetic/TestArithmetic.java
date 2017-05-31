package cases.novemser.el.value.arithmetic;

import cases.TestResource;
import org.apache.el.lang.ELArithmetic;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static cases.TestResource.logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.literal.arithmetic
 * Author:  Novemser
 * 2017/5/29
 */
public class TestArithmetic {
    private static final String a = "1.1";
    private static final BigInteger b = new BigInteger("1000000000000000000000");

    @Test
    public void test0001() {
        TestResource.testExpression("DN-21-0001",
                "${(2*2)%3%2}", "1");
    }

    @Test
    public void test0002() throws Exception {
        TestResource.testExpression("DN-21-0002",
                "${2*2+2*2}", "8"
        );
    }

    @Test
    public void test0003() throws Exception {
        TestResource.testExpression("DN-21-0003",
                "${2+2+20%2}", "4"
        );
    }

    @Test
    public void test0004() throws Exception {
        TestResource.testExpression("DN-21-0004",
                "${2m2*20%2}", ""
        );
    }

    @Test
    public void test0005() throws Exception {
        TestResource.testExpression("DN-21-0005",
                "${2+2m20%2}", ""
        );
    }

    @Test
    public void test0006() throws Exception {
        TestResource.testExpression("DN-21-0006",
                "${2+2*20j2}", ""
        );
    }

    @Test
    public void test0007() throws Exception {
        TestResource.testExpression("DN-21-0007",
                "${(1+0+2+2*2)%3}", "1");
    }

    @Test
    public void test0008() throws Exception {
        TestResource.testExpression("DN-21-0008",
                "${(1*0*2+2*2)%3}", "1");
    }

    @Test
    public void test0009() throws Exception {
        TestResource.testExpression("DN-21-0009",
                "${(1%10+2*20)%6%3}", "2");
    }

    @Test
    public void test0010() throws Exception {
        testAddEquals("DN-21-0071", 0L, null, null);
        testAddEquals("DN-21-0072", 2d, "1.", "1");
        testAddEquals("DN-21-0073", 1L, "", 1);
        testAddEquals("DN-21-0074", 3d, "", "3.");
    }

    @Test
    public void test0011() throws Exception {
        // a b 先后顺序对结果并无影响
        testArithmeticAdd("DN-21-0075", "1000000000000000000001.1", a, b);
        testArithmeticAdd("DN-21-0076", "-999999999999999999998.9", a, b.negate());
        testArithmeticMultiply("DN-21-0077", "1100000000000000000000.0", a, b);
        testArithmeticDivide("DN-21-0078", "100000000000000000000", b, "10");

        int a = 101;
        int b = 202;
        testArithmeticAdd("DN-21-0096", "303", a, b);
        BigInteger bi = new BigInteger("123456789876543210");
        testArithmeticAdd("DN-21-0097", "123456789876543211", bi, 1);
        BigDecimal bd = new BigDecimal("0.1234567898765432112345678987654321");
        testArithmeticAdd("DN-21-0098", "1.1234567898765432112345678987654321", bd, 1);
        testArithmeticAdd("DN-21-0099", "123456789876543210.1234567898765432112345678987654321", bd, bi);
        testArithmeticAdd("DN-21-0100", "1", null, 1);
        testArithmeticAdd("DN-21-0200", "1", "0", 1);

    }

    @Test
    public void testDivideWhiteBox() {
        BigDecimal bd = new BigDecimal("123.321");
        BigInteger bi = new BigInteger("12321");
        testArithmeticDivide("DN-21-0202", "0", null, null);
        testArithmeticDivide("DN-21-0203", "0", null, bd);
        testArithmeticDivide("DN-21-0204", "0", null, bi);

        try {
            ELArithmetic.divide(bd, null);
            logger.error("Test case didn't throw exception in DN-21-0205");
            fail();
        } catch (Exception e) {
            logger.info("DN-21-0205 passed.");
        }

        try {
            ELArithmetic.divide(bi, null);
            logger.error("Test case didn't throw exception in DN-21-0206");
            fail();
        } catch (Exception e) {
            logger.info("DN-21-0206 passed.");
        }
        bi = new BigInteger("5");
        bd = new BigDecimal("2.5");
        testArithmeticDivide("DN-21-0207", "2", 10L, bi);
        testArithmeticDivide("DN-21-0208", "4", 10L, bd);
        testArithmeticDivide("DN-21-0209", "0.0", null, 1.1);
        testArithmeticDivide("DN-21-0210", "1.0", 10L, 10);
    }

    @Test
    public void test0012() throws Exception {
        assertEquals("-999999999999999999998.9",
                String.valueOf(ELArithmetic.subtract(a, b)));
    }

    public void testArithmeticDivide(String passTestCase, String result, Object left, Object right) {
        try {
            assertEquals(result, String.valueOf(ELArithmetic.divide(left, right)));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testArithmeticMultiply(String passTestCase, String result, Object left, Object right) {
        try {
            assertEquals(result, String.valueOf(ELArithmetic.multiply(left, right)));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testArithmeticAdd(String passTestCase, String result, Object left, Object right) {
        try {
            assertEquals(result, String.valueOf(ELArithmetic.add(left, right)));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testAddEquals(String passTestCase, Number result, Object left, Object right) {
        try {
            assertEquals(result, ELArithmetic.add(left, right));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }
}
