package cases.novemser.el.value.relational;

import cases.TestResource;
import org.junit.Test;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.value.relational
 * Author:  Novemser
 * 2017/5/30
 */
public class TestAnd {
    @Test
    public void test0001() {
        TestResource.testExpression("DN-21-0010",
                "${true && false}", "false");
    }

    @Test
    public void test0002() throws Exception {
        TestResource.testExpression("DN-21-0011",
                "${true && null}", "false"
        );
    }

    @Test
    public void test0003() throws Exception {
        TestResource.testExpression("DN-21-0012",
                "${null && null}", "false"
        );
    }

    @Test
    public void test0004() throws Exception {
        TestResource.testExpression("DN-21-0013",
                "${false && null}", "false"
        );
    }
}
