package cases.novemser.el.value.arithmetic;

import cases.TestResource;
import org.junit.Test;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.literal.arithmetic
 * Author:  Novemser
 * 2017/5/29
 */
public class TestDN210001_0009 {
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
}
