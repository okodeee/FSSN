package cse.khu.FSSN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class lec_06_prg_02_http_web_client {
    public static void main(String[] args) {
        System.out.println("## HTTP client started.");

        try {
            // GET request for http://localhost:8080/temp/
            System.out.println("## GET request for http://localhost:8080/temp/");
            String response = sendGetRequest("http://localhost:8080/temp/");
            System.out.println("## GET response [start]");
            System.out.println(response);
            System.out.println("## GET response [end]");

            // GET request for http://localhost:8080/?var1=9&var2=9
            System.out.println("## GET request for http://localhost:8080/?var1=9&var2=9");
            response = sendGetRequest("http://localhost:8080/?var1=9&var2=9");
            System.out.println("## GET response [start]");
            System.out.println(response);
            System.out.println("## GET response [end]");

            // POST request for http://localhost:8080/ with var1 is 9 and var2 is 9
            System.out.println("## POST request for http://localhost:8080/ with var1 is 9 and var2 is 9");
            response = sendPostRequest("http://localhost:8080/", "var1=9&var2=9");
            System.out.println("## POST response [start]");
            System.out.println(response);
            System.out.println("## POST response [end]");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("## HTTP client completed.");
    }

    private static String sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return content.toString();
    }

    private static String sendPostRequest(String urlString, String urlParameters) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = urlParameters.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return content.toString();
    }
}
