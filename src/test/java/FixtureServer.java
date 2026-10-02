import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Local copy of the store search used by the tests on every pull request, so
 * CI does not depend on a third-party site (spec 001 FR-2, decision D1).
 * Like WooCommerce, a search that matches a single product redirects to it.
 */
final class FixtureServer implements AutoCloseable {

    private final HttpServer server;

    FixtureServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", this::handle);
        server.start();
    }

    String baseUrl() {
        return "http://127.0.0.1:" + server.getAddress().getPort() + "/";
    }

    private void handle(HttpExchange ex) throws IOException {
        String path = ex.getRequestURI().getPath();
        String query = ex.getRequestURI().getRawQuery();
        if (path.equals("/product/camera/")) {
            send(ex, 200, "camera.html");
        } else if (path.equals("/") && query != null && "camera".equalsIgnoreCase(param(query, "s"))) {
            ex.getResponseHeaders().add("Location", "/product/camera/");
            ex.sendResponseHeaders(302, -1);
            ex.close();
        } else if (path.equals("/")) {
            send(ex, 200, "index.html");
        } else {
            ex.sendResponseHeaders(404, -1);
            ex.close();
        }
    }

    private static String param(String query, String name) {
        for (String pair : query.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv[0].equals(name)) {
                return kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "";
            }
        }
        return null;
    }

    private void send(HttpExchange ex, int status, String resource) throws IOException {
        try (InputStream in = getClass().getResourceAsStream("/fixture/" + resource)) {
            if (in == null) {
                throw new IOException("fixture ausente: " + resource);
            }
            byte[] body = in.readAllBytes();
            ex.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
            ex.sendResponseHeaders(status, body.length);
            try (OutputStream out = ex.getResponseBody()) {
                out.write(body);
            }
        }
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
