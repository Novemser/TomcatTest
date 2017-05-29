package cases.hugansen;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.hugansen
 * Author:  Novemser
 * 2017/5/26
 */
public class TestCase50408 extends TomcatBaseTest {
    private static CountDownLatch bug55772Latch1 = new CountDownLatch(1);
    private static CountDownLatch bug55772Latch2 = new CountDownLatch(1);
    private static CountDownLatch bug55772Latch3 = new CountDownLatch(1);
    private static boolean bug55772IsSecondRequest = false;
    private static boolean bug55772RequestStateLeaked = false;

    private static class Bug55772Servlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (bug55772IsSecondRequest) {
                Cookie[] cookies = req.getCookies();
                if (cookies != null && cookies.length > 0) {
                    for (Cookie cookie : req.getCookies()) {
                        if (cookie.getName().equalsIgnoreCase("something.that.should.not.leak")) {
                            bug55772RequestStateLeaked = true;
                        }
                    }
                }
                bug55772Latch3.countDown();
            } else {
                req.getCookies(); // We have to do this so Tomcat will actually parse the cookies from the request
            }

            req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", Boolean.TRUE);
            AsyncContext asyncContext = req.startAsync();
            asyncContext.setTimeout(5000);

            bug55772Latch1.countDown();

            PrintWriter writer = asyncContext.getResponse().getWriter();
            writer.print('\n');
            writer.flush();

            bug55772Latch2.countDown();
        }
    }

    @Test
    public void testBug55772() throws Exception {
        Tomcat tomcat = getTomcatInstance();
        tomcat.getConnector().setProperty("processorCache", "1");
        tomcat.getConnector().setProperty("maxThreads", "1");

        // No file system docBase required
        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "async", new Bug55772Servlet());
        ctx.addServletMapping("/*", "async");
//        ctx.addServletMappingDecoded("/*", "async");

        tomcat.start();

        String request1 = "GET /async?1 HTTP/1.1\r\n" +
                "Host: localhost:" + getPort() + "\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n" +
                "User-Agent: Request1\r\n" +
                "Accept-Encoding: gzip,deflate,sdch\r\n" +
                "Accept-Language: en-US,en;q=0.8,fr;q=0.6,es;q=0.4\r\n" +
                "Cookie: something.that.should.not.leak=true\r\n" +
                "\r\n";

        String request2 = "GET /async?2 HTTP/1.1\r\n" +
                "Host: localhost:" + getPort() + "\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n" +
                "User-Agent: Request2\r\n" +
                "Accept-Encoding: gzip,deflate,sdch\r\n" +
                "Accept-Language: en-US,en;q=0.8,fr;q=0.6,es;q=0.4\r\n" +
                "\r\n";

        try (final Socket connection = new Socket("localhost", getPort())) {
            connection.setSoLinger(true, 0);
            Writer writer = new OutputStreamWriter(connection.getOutputStream(),
                    StandardCharsets.US_ASCII);
            writer.write(request1);
            writer.flush();

            bug55772Latch1.await();
            connection.close();
        }

        bug55772Latch2.await();
        bug55772IsSecondRequest = true;

        try (final Socket connection = new Socket("localhost", getPort())) {
            connection.setSoLinger(true, 0);
            Writer writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(request2);
            writer.flush();
            connection.getInputStream().read();
        }

        bug55772Latch3.await();
        if (bug55772RequestStateLeaked) {
            Assert.fail("State leaked between requests!");
        }
    }

    @Test
    public void testBug54888() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-3.0");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54888.jsp", res, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        String body = res.toString();
        Assert.assertTrue(body.contains("OK - 1"));
        Assert.assertTrue(body.contains("OK - 2"));
        Assert.assertTrue(body.contains("OK - 3"));
    }
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

    @BeforeClass
    public void setUpTomcat() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-3.0");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();
    }

    @Test
    public void testMainJsp() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-3.0");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/testPackage/main.jsp", res, null);
        String body = res.toString();
        System.out.println(body);
    }
}
