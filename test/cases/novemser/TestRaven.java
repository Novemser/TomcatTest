package cases.novemser;

import com.getsentry.raven.Raven;
import org.apache.log4j.Logger;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser
 * Author:  Novemser
 * 2017/5/29
 */
public class TestRaven {
    private static final String DNS = "https://30c0bab11f3f421d94c0b5871ed7f4eb:d3c202de9ae24ca4b03477e5ed9d08fc@sentry.io/170641";
    private static final Logger logger = Logger.getLogger(TestRaven.class);
    private static Raven raven;

    public static void main(String[] args) {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");

        try {
            int example = 1 / 0;
        } catch (Exception e) {
            logger.error("Caught exception!", e);
        }
        logger.trace("sdas");
    }
//
//    void logSimpleMessage() {
//        // This sends a simple event to Sentry
//        logger.error("This is a test");
//    }
//
//    void logWithBreadcrumbs() {
//        // Record a breadcrumb that will be sent with the next event(s),
//        // by default the last 100 breadcrumbs are kept.
//        Breadcrumbs.record(
//                new BreadcrumbBuilder().setMessage("User made an action").build()
//        );
//
//        // This sends a simple event to Sentry
//        logger.error("This is a test");
//    }
//
//    void logWithExtras() {
//        // MDC extras
//        MDC.put("extra_key", "extra_value");
//        // NDC extras are sent under 'log4J-NDC'
//        NDC.push("Extra_details");
//        // This sends an event with extra data to Sentry
//        logger.error("This is a test");
//    }
//
//    void logException() {
//        try {
//            unsafeMethod();
//        } catch (Exception e) {
//            // This sends an exception event to Sentry
//            logger.error("Exception caught", e);
//        }
//    }
//
    void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }
}
