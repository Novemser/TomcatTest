package cases.novemser.el.value.conditional;

import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import static cases.TestResource.logger;
import static cases.TestResource.testExpression;
import static org.junit.Assert.fail;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.value.conditional
 * Author:  Novemser
 * 2017/5/31
 */
public class TestConditional {
    @Test
    public void test01() {
        testExpression("DN-21-0087", "${true? 1:2}", "1");
        testExpression("DN-21-0088", "${true? null:2}", "");
        testExpression("DN-21-0089", "${true? \'Hello\':2}", "Hello");
        testExpression("DN-21-0090", "${null? 1:2}", "2");
        testExpression("DN-21-0091", "${null? null:2}", "2");
        testExpression("DN-21-0092", "${null? \"Hello\":2}", "2");

        ExpressionFactory factory = ExpressionFactory.newInstance();
        ELContext context = new ELContextImpl();
        try {
            ValueExpression e = factory.createValueExpression(context, "${1?3:2}", String.class);
            e.getValue(context);
            logger.error("Exception not thrown in test DN-21-0093:int 1 is not boolean");
            fail();
        } catch (Throwable e) {
            logger.info("DN-21-0093 passed.");
        }

        try {
            ValueExpression e = factory.createValueExpression(context, "${false? ?:2}", String.class);
            e.getValue(context);
            logger.error("Exception not thrown in test DN-21-0094:invalid operand");
            fail();
        } catch (Throwable e) {
            logger.info("DN-21-0094 passed.");
        }

        try {
            ValueExpression e = factory.createValueExpression(context, "${false? 66:aasf^}", String.class);
            e.getValue(context);
            logger.error("Exception not thrown in test DN-21-0095:invalid operand");
            fail();
        } catch (Throwable e) {
            logger.info("DN-21-0095 passed.");
        }
    }
}
