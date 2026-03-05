import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubService {
    public static String fetchEvent(String username) throws Exception {
        String url = "https://api.github.com/users/" + username + "/events";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}