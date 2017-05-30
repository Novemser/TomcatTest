package cases.novemser.el.method;

import org.apache.el.ExpressionFactoryImpl;
import org.apache.jasper.el.ELContextImpl;

import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import java.lang.reflect.Method;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.method
 * Author:  Novemser
 * 2017/5/30
 */
public class TestMethodInvocation {


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
                    m = TestFunctions.class.getMethod("sout", Void.class);
                else if ("soutStr".equals(localName))
                    m = TestFunctions.class.getMethod("soutStr", String.class);
                else if ("soutStrs".equals(localName))
                    m = TestFunctions.class.getMethod("soutStrs", String[].class);
            } catch (Exception ignored) {

            }


            return m;
        }
    }
}
