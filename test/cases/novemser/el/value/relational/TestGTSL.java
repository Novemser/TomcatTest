package cases.novemser.el.value.relational;

import cases.TestResource;
import org.junit.Test;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.value.logical
 * Author:  Novemser
 * 2017/5/30
 */
public class TestGTSL {
//    private static final BigDecimal a = new BigDecimal("2.2");
//    private static final BigDecimal b = new BigDecimal("4.4");
//    private static final BigInteger c = new BigInteger("3");
//    private static final BigInteger d = new BigInteger("1");

    @Test
    public void test0001() {
        TestResource.testExpression("DN-21-0234",
                "${7<=7}", "true");
    }

    @Test
    public void test0002() {
        TestResource.testExpression("DN-21-0235",
                "${7 le 7}", "true");
    }

    @Test
    public void test0003() {
        TestResource.testExpression("DN-21-0236",
                "${7>=7}", "true");
    }

    @Test
    public void test0004() {
        TestResource.testExpression("DN-21-0237",
                "${7 ge 7}", "true");
    }

    @Test
    public void test0005() {
        TestResource.testExpression("DN-21-0238",
                "${3.15<null}", "false");
    }

    @Test
    public void test0006() {
        TestResource.testExpression("DN-21-0239",
                "${3.15 lt null}", "false");
    }

    @Test
    public void test0007() {
        TestResource.testExpression("DN-21-0240",
                "${3.15>null}", "false");
    }

    @Test
    public void test0008() {
        TestResource.testExpression("DN-21-0241",
                "${3.15 gt null}", "false");
    }

    @Test
    public void test0009() {
        TestResource.testExpression("DN-21-0242",
                "${3.15<=null}", "false");
    }

    @Test
    public void test0010() {
        TestResource.testExpression("DN-21-0243",
                "${3.15 le null}", "false");
    }

    @Test
    public void test0011() {
        TestResource.testExpression("DN-21-0244",
                "${3.15>=null}", "false");
    }

    @Test
    public void test0012() {
        TestResource.testExpression("DN-21-0245",
                "${3.15 ge null}", "false");
    }

    @Test
    public void test0013() {
        TestResource.testExpression("DN-21-0246",
                "${null<4}", "false");
    }

    @Test
    public void test0014() {
        TestResource.testExpression("DN-21-0247",
                "${null lt 4}", "false");
    }

    @Test
    public void test0015() {
        TestResource.testExpression("DN-21-0248",
                "${null>4}", "false");
    }

    @Test
    public void test0016() {
        TestResource.testExpression("DN-21-0249",
                "${null gt 4}", "false");
    }

    @Test
    public void test0017() {
        TestResource.testExpression("DN-21-0250",
                "${null<=4}", "false");
    }

    @Test
    public void test0018() {
        TestResource.testExpression("DN-21-0251",
                "${null le 4}", "false");
    }

    @Test
    public void test0019() {
        TestResource.testExpression("DN-21-0252",
                "${null>=4}", "false");
    }

    @Test
    public void test0020() {
        TestResource.testExpression("DN-21-0253",
                "${null ge 4}", "false");
    }

//    @Test
//    public void test0021() {
//        testBigDecimalCompare("DN-21-0254", "-1",  a, b);
//    }
//
//    @Test
//    public void test0022() {
//        testBigIntegerCompare("DN-21-0255", "1", c, d);
//    }

    @Test
    public void test0023() {
        TestResource.testExpression("DN-21-0254",
                "${3.444444<6.777777777777777}", "true");
    }


//    public void testBigDecimalCompare(String passTestCase, String result, Comparable left, Comparable right) {
//        try {
//            assertEquals(result, String.valueOf(left.compareTo(right)));
//        }
//        catch (Throwable e) {
//            logger.error("Exception caught in test " + passTestCase + ":", e);
//            return;
//        }
//        logger.info(passTestCase + " passed.");
//    }
//
//    public void testBigIntegerCompare(String passTestCase, String result, Comparable left, Comparable right) {
//        try {
//            assertEquals(result, String.valueOf(left.compareTo(right)));
//        }
//        catch (Throwable e) {
//            logger.error("Exception caught in test " + passTestCase + ":", e);
//            return;
//        }
//        logger.info(passTestCase + " passed.");
//    }

}
