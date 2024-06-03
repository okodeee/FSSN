package cse.khu.FSSN;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class lec_06_prg_08_rest_client_v3 {
    private static final String BASE_URL = "http://127.0.0.1:8080/membership_api/";

    public static void main(String[] args) {
        try {
            // Reads a non-registered member: error-case
            sendGetRequest("0001", 1);

            // Creates a new registered member: non-error case
            sendPostRequest("0001", "apple", 2);

            // Reads a registered member: non-error case
            sendGetRequest("0001", 3);

            // Creates an already registered member: error case
            sendPostRequest("0001", "xpple", 4);

            // Updates a non-registered member: error case
            sendPutRequest("0002", "xrange", 5);

            // Updates a registered member: non-error case
            sendPostRequest("0002", "xrange", 6); // Intermediate step to register
            sendPutRequest("0002", "orange", 6);

            // Deletes a registered member: non-error case
            sendDeleteRequest("0001", 7);

            // Deletes a non-registered member: non-error case
            sendDeleteRequest("0001", 8);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendGetRequest(String memberId, int step) throws Exception {
        URL url = new URL(BASE_URL + memberId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        String response = getResponse(connection);

        JSONObject jsonResponse = new JSONObject(response);
        String result = jsonResponse.optString(memberId, "N/A");

        System.out.println("#" + step + " Code: " + responseCode + " >> JSON: " + jsonResponse + " >> JSON Result: " + result);
    }

    private static void sendPostRequest(String memberId, String memberValue, int step) throws Exception {
        URL url = new URL(BASE_URL + memberId + "?value=" + URLEncoder.encode(memberValue, "UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        connection.setRequestProperty("Content-Type", "application/json");
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes( memberId + "=" + memberValue);
        out.flush();
        out.close();

        int responseCode = connection.getResponseCode();
        String response = getResponse(connection);

        JSONObject jsonResponse = new JSONObject(response);
        String result = jsonResponse.optString(memberId, "N/A");

        System.out.println("#" + step + " Code: " + responseCode + " >> JSON: " + jsonResponse + " >> JSON Result: " + result);
    }

    private static void sendPutRequest(String memberId, String memberValue, int step) throws Exception {
        URL url = new URL(BASE_URL + memberId + "?value=" + URLEncoder.encode(memberValue, "UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);

        connection.setRequestProperty("Content-Type", "application/json");
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(memberId + "=" + memberValue);
        out.flush();
        out.close();

        int responseCode = connection.getResponseCode();
        String response = getResponse(connection);

        JSONObject jsonResponse = new JSONObject(response);
        String result = jsonResponse.optString(memberId, "N/A");

        System.out.println("#" + step + " Code: " + responseCode + " >> JSON: " + jsonResponse + " >> JSON Result: " + result);
    }

    private static void sendDeleteRequest(String memberId, int step) throws Exception {
        URL url = new URL(BASE_URL + memberId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        int responseCode = connection.getResponseCode();
        String response = getResponse(connection);

        JSONObject jsonResponse = new JSONObject(response);
        String result = jsonResponse.optString(memberId, "N/A");

        System.out.println("#" + step + " Code: " + responseCode + " >> JSON: " + jsonResponse + " >> JSON Result: " + result);
    }

    private static String getResponse(HttpURLConnection connection) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        return content.toString();
    }
}
