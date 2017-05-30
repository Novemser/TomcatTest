package cases.novemser.el.value.relational;

import cases.TestResource;
import org.junit.Test;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.value.relational
 * Author:  Novemser
 * 2017/5/30
 */
public class TestNot {
    @Test
    public void test0001() {
        TestResource.testExpression("DN-21-0018",
                "${!true}", "false");
    }

    @Test
    public void test0002() throws Exception {
        TestResource.testExpression("DN-21-0019",
                "${!false}", "true"
        );
    }

    @Test
    public void test0003() throws Exception {
        TestResource.testExpression("DN-21-0020",
                "${!null}", "true"
        );
    }
}
