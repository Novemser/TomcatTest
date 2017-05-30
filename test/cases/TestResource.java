package cases;

import org.apache.jasper.el.ELContextImpl;
import org.apache.log4j.Logger;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import static org.junit.Assert.assertEquals;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases
 * Author:  Novemser
 * 2017/5/29
 */
public class TestResource {
    public static final Logger logger = Logger.getLogger(TestResource.class);

    /**
     * 表达式测试
     *
     * @param instance 对象
     * @param expected 预期的值
     */
    public static void testExpression(String passTestCase, Object instance, String expected) {
        ExpressionFactory factory = ExpressionFactory.newInstance();
        ELContext context = new ELContextImpl();
        try {
            ValueExpression ve = factory.createValueExpression(instance, String.class);

            String result = (String) ve.getValue(context);

            assertEquals(expected, result);
        } catch (Throwable e) {
            logger.error("Exception caught in EL expression test:", e);
            return;
        }


        logger.info(passTestCase + " passed.");
    }

    /**
     * 表达式测试
     *
     * @param expression 表达式
     * @param expected   预期的值
     */
    public static void testExpression(String passTestCase, String expression, String expected) {
        ExpressionFactory factory = ExpressionFactory.newInstance();
        ELContext context = new ELContextImpl();
        try {
            ValueExpression ve = factory.createValueExpression(
                    context, expression, String.class);

            String result = (String) ve.getValue(context);


            assertEquals(expected, result);
        } catch (Throwable e) {
            logger.error("Exception caught in test " + passTestCase + ":", e);
            throw e;
        }


        logger.info(passTestCase + " passed.");
    }
}
