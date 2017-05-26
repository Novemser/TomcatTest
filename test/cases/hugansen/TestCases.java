package cases.hugansen;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.hugansen
 * Author:  Novemser
 * 2017/5/26
 */
public class TestCases extends TomcatBaseTest {
    @Test
    public void testBug50408() throws Exception {
//        getTomcatInstanceTestWebapp(true, true);
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-3.0");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();
        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug50408.jsp", new ByteChunk(), null);

        assertEquals(HttpServletResponse.SC_OK, rc);
    }
}
