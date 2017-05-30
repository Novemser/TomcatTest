package cases.novemser.el.value.arithmetic;

import cases.TestResource;
import org.apache.el.lang.ELArithmetic;
import org.junit.Test;

import java.math.BigInteger;

import static cases.TestResource.logger;
import static org.junit.Assert.assertEquals;

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
        testBigDecimalAdd("DN-21-0075", "1000000000000000000001.1", a, b);
        testBigDecimalAdd("DN-21-0076", "-999999999999999999998.9", a, b.negate());
        testBigDecimalMultiply("DN-21-0077", "1100000000000000000000.0", a, b);
        testBigDecimalDivide("DN-21-0078", "100000000000000000000", b, "10");
    }

    @Test
    public void test0012() throws Exception {
        assertEquals("-999999999999999999998.9",
                String.valueOf(ELArithmetic.subtract(a, b)));
    }

    public void testBigDecimalDivide(String passTestCase, String result, Object left, Object right) {
        try {
            assertEquals(result, String.valueOf(ELArithmetic.divide(left, right)));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testBigDecimalMultiply(String passTestCase, String result, Object left, Object right) {
        try {
            assertEquals(result, String.valueOf(ELArithmetic.multiply(left, right)));
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            return;
        }
        logger.info(passTestCase + " passed.");
    }

    public void testBigDecimalAdd(String passTestCase, String result, Object left, Object right) {
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
