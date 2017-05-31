package cases.novemser.el.method;

import org.apache.el.ExpressionFactoryImpl;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import java.lang.reflect.Method;

import static cases.TestResource.logger;
import static org.junit.Assert.assertEquals;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.method
 * Author:  Novemser
 * 2017/5/30
 */
public class TestMethodInvocation {
    @Test
    public void test0001() throws Exception {
        testFunctionCall("DN-21-0079", "${fn:sout()}", "");
        testFunctionCall("DN-21-0080", "${fn:soutStr(\"This should be shown on screen\")}", "");
        testFunctionCall("DN-21-0081", "${fn:soutStrs(\"1\", \"2\", \"3\")}", "");
        testFunctionCall("DN-21-0082", "${fn:getGreeting()}", "Hello");
        testFunctionCall("DN-21-0083", "${fn:trim(\" Hi \")}", "Hi");
        testFunctionCall("DN-21-0084", "${fn:concat('O','K')}", "OK");
        testFunctionCall("DN-21-0085", "${fn:concat(fn:toArray('O','K'))}", "OK");
        testFunctionCall("DN-21-0086", "${fn:concat2('RU', fn:toArray('O','K'))}", "RUOK");
    }

    private void testFunctionCall(String testCaseName, String expression, String expected) {
        try {
            String result = evaluateExpression(expression);
            assertEquals(expected, result);
        } catch (Throwable e) {
            logger.error("Exception caught in test " + testCaseName + ":", e);
            return;
        }

        logger.info(testCaseName + " passed.");
    }

    private String evaluateExpression(String expression) {
        ExpressionFactoryImpl exprFactory = new ExpressionFactoryImpl();
        ELContextImpl ctx = new ELContextImpl();
        ctx.setFunctionMapper(new FMapper());
        ValueExpression ve = exprFactory.createValueExpression(ctx, expression,
                String.class);
        return (String) ve.getValue(ctx);
    }

    public static class FMapper extends FunctionMapper {

        @Override
        public Method resolveFunction(String prefix, String localName) {
            Method m = null;
            try {
                if ("trim".equals(localName)) {
                    m = TestFunctions.class.getMethod("trim", String.class);
                } else if ("concat".equals(localName)) {
                    m = TestFunctions.class.getMethod("concat", String[].class);
                } else if ("concat2".equals(localName)) {
                    m = TestFunctions.class.getMethod("concat2", String.class, String[].class);
                } else if ("toArray".equals(localName)) {
                    m = TestFunctions.class.getMethod("toArray", String.class, String.class);
                } else if ("sout".equals(localName))
                    m = TestFunctions.class.getMethod("sout");
                else if ("soutStr".equals(localName))
                    m = TestFunctions.class.getMethod("soutStr", String.class);
                else if ("soutStrs".equals(localName))
                    m = TestFunctions.class.getMethod("soutStrs", String[].class);
                else if ("getGreeting".equals(localName))
                    m = TestFunctions.class.getMethod("getGreeting");
            } catch (Exception ignored) {

            }

            return m;
        }
    }
}
