package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIService {
    private static final String BASE_URL = "http://23021510.pythonanywhere.com/api";

    public String APISignin(String username, String password) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/signin/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}