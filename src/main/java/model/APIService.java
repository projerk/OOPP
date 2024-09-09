package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class APIService {
    private static final String BASE_LOGIN_URL = "http://23021510.pythonanywhere.com/api";
    private static final String BASE_THESAURUS_URL = "https://api.api-ninjas.com/v1/thesaurus?word=";
    private static final String ThesaurusAPI_KEY = "4JlehrF24Uh+Ke+U65DjIQ==HiNfHPBvPZEaBLGI";

    public int APISignin(String username, String password) {
        HttpClient client = HttpClient.newHttpClient();
        
        String requestBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_LOGIN_URL + "/signin/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();
            return statusCode;
            // if (statusCode == 200) {
            //     return "Signin successful: " + responseBody;
            // } else if (statusCode == 401) {
            //     return "Signin failed: Incorrect username or password.";
            // } else if (statusCode == 400) {
            //     return "Signin failed: Invalid request.";
            // } else {
            //     return "Signin failed: Server error or unexpected response. Status code: " + statusCode;
            // }
        } catch (Exception e) {
            return 0;
        }
    }

    public JSONObject APIThesaurus(String word) {
        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_THESAURUS_URL + word))
            .header("X-Api-Key", ThesaurusAPI_KEY)
            .GET()
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                return jsonResponse;
            
            } else {
                JSONObject jsonResponse = new JSONObject("{'error' : 'true'}");
                return jsonResponse;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject("{'error' : 'true'}");
        return jsonResponse;
    }
}