package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIService {
    private static final String BASE_URL = "http://23021510.pythonanywhere.com/api";

    public int APISignin(String username, String password) {
        HttpClient client = HttpClient.newHttpClient();
        
        String requestBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/signin/"))
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
}