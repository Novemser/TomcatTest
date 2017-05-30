package cases.novemser.el.value.relational;

import cases.TestResource;
import org.junit.Test;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.value.logical
 * Author:  Novemser
 * 2017/5/30
 */
public class TestEQ {
    @Test
    public void test0001() {
        TestResource.testExpression("DN-21-0021",
                "${true==9}", "false");
    }

    @Test
    public void test0002() throws Exception {
        TestResource.testExpression("DN-21-0022",
                "${null==false}", "false"
        );
    }

    @Test
    public void test0003() throws Exception {
        TestResource.testExpression("DN-21-0023",
                "${1!=-1}", "true"
        );
    }

    @Test
    public void test0004() throws Exception {
        TestResource.testExpression("DN-21-0024",
                "${2.3333333333333333333333333333333333333333333333333333333333332==2.3333333333333333333333333333333333333333333333333333333333333}", "false"
        );
    }

    @Test
    public void test0005() throws Exception {
        TestResource.testExpression("DN-21-0025",
                "${1.1!=1.1}", "false"
        );
    }

    @Test
    public void test0006() throws Exception {
        TestResource.testExpression("DN-21-0026",
                "${\"str\"==\"str\"}", "true"
        );
    }

    @Test
    public void test0007() throws Exception {
        TestResource.testExpression("DN-21-0027",
                "${\"1\"==1}", "true"
        );
    }

    @Test
    public void test0008() throws Exception {
        TestResource.testExpression("DN-21-0028",
                "${1.0==1}", "true"
        );
    }

    @Test
    public void test0009() throws Exception {
        TestResource.testExpression("DN-21-0029",
                "${0.0==0}", "true"
        );
    }

    @Test
    public void test0010() throws Exception {
        TestResource.testExpression("DN-21-0030",
                "${0==-0}", "true"
        );
    }

    @Test
    public void test0011() throws Exception {
        TestResource.testExpression("DN-21-0031",
                "${\"2.3333333333333333333333333333333333333333333333333333333333333\"==2.3333333333333333333333333333333333333333333333333333333333333}", "false"
        );
        TestResource.testExpression("DN-21-0033",
                "${\"2.3\"==2.3}", "true"
        );
    }
}
