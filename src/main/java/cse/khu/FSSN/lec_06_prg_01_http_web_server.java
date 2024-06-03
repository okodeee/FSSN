package cse.khu.FSSN;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class lec_06_prg_01_http_web_server {
    public static void main(String[] args) throws IOException {
        String serverName = "localhost";
        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverName, serverPort), 0);

        server.createContext("/", new MyHttpHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("## HTTP server started at http://" + serverName + ":" + serverPort);

        server.start();
    }

    static class MyHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();
            if ("GET".equalsIgnoreCase(requestMethod)) {
                doGet(exchange);
            } else if ("POST".equalsIgnoreCase(requestMethod)) {
                doPost(exchange);
            }
        }

        private void printHttpRequestDetail(HttpExchange exchange) {
            System.out.println("::Client address   : " + exchange.getRemoteAddress().getAddress());
            System.out.println("::Client port      : " + exchange.getRemoteAddress().getPort());
            System.out.println("::Request command  : " + exchange.getRequestMethod());
            System.out.println("::Request path      : " + exchange.getRequestURI());
            System.out.println("::Request headers  : " + exchange.getRequestHeaders());
        }

        private void sendHttpResponseHeader(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, 0);
        }

        private void doGet(HttpExchange exchange) throws IOException {
            System.out.println("## do_GET() activated.");
            printHttpRequestDetail(exchange);
            sendHttpResponseHeader(exchange);

            String response;
            String path = exchange.getRequestURI().getPath();
            String query = exchange.getRequestURI().getQuery();

            if (query != null) {
                Map<String, Integer> parameters = parameterRetrieval(query);
                int result = simpleCalc(parameters.get("var1"), parameters.get("var2"));
                response = "<html>GET request for calculation => " + parameters.get("var1") + " x " + parameters.get("var2") + " = " + result + "</html>";
                System.out.println("## GET request for calculation => " + parameters.get("var1") + " x " + parameters.get("var2") + " = " + result);
            } else {
                response = "<html><p>HTTP Request GET for Path: " + path + "</p></html>";
                System.out.println("## GET request for directory => " + path);
            }

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }

        private void doPost(HttpExchange exchange) throws IOException {
            System.out.println("## do_POST() activated.");
            printHttpRequestDetail(exchange);
            sendHttpResponseHeader(exchange);

            int contentLength = Integer.parseInt(exchange.getRequestHeaders().getFirst("Content-Length"));
            byte[] data = new byte[contentLength];
            exchange.getRequestBody().read(data);
            String postData = new String(data);

            Map<String, Integer> parameters = parameterRetrieval(postData);
            int result = simpleCalc(parameters.get("var1"), parameters.get("var2"));

            String response = "POST request for calculation => " + parameters.get("var1") + " x " + parameters.get("var2") + " = " + result;
            System.out.println("## POST request data => " + postData);
            System.out.println("## POST request for calculation => " + parameters.get("var1") + " x " + parameters.get("var2") + " = " + result);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }

        private int simpleCalc(int para1, int para2) {
            return para1 * para2;
        }

        private Map<String, Integer> parameterRetrieval(String query) {
            Map<String, Integer> result = new HashMap<>();
            String[] fields = query.split("&");
            for (String field : fields) {
                String[] keyValue = field.split("=");
                result.put(keyValue[0], Integer.parseInt(keyValue[1]));
            }
            return result;
        }
    }
}
