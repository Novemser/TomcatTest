package cases.novemser.el.value.logical;

import cases.TestResource;
import org.junit.Test;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.value.relational
 * Author:  Novemser
 * 2017/5/30
 */
public class TestOr {
    @Test
    public void test0001() {
        TestResource.testExpression("DN-21-0014",
                "${true || false}", "true");
    }

    @Test
    public void test0002() throws Exception {
        TestResource.testExpression("DN-21-0015",
                "${true || null}", "true"
        );
    }

    @Test
    public void test0003() throws Exception {
        TestResource.testExpression("DN-21-0016",
                "${null || null}", "false"
        );
    }

    @Test
    public void test0004() throws Exception {
        TestResource.testExpression("DN-21-0017",
                "${false || null}", "false"
        );
    }
}
