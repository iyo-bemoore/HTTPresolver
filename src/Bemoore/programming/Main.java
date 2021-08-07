package Bemoore.programming;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException {
     /*   BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        URL url = new URL("https://jsonplaceholder.typicode.com/albums");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

       // requests
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();
        int status = connection.getResponseCode();
        System.out.println(status);
        System.out.println(responseContent.toString());*/
        httpClientRequest();
    }
    // method 2
    public static void httpClientRequest() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/albums")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Main::JSONParser)
                .join();
    }

    public static String JSONParser(String responseBody){
        JSONArray albums = new JSONArray(responseBody);
        int len = albums.length();
        for (int i = 0; i < len; i++ ) {
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userID = album.getInt("userId");
            String title = album.getString("title");
            System.out.println(id + " " + title + " " + userID);
        }
        return null;
    }
}
