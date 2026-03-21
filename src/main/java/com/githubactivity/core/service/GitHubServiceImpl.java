package com.githubactivity.core.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.githubactivity.core.exception.ApiException;
import com.githubactivity.core.exception.RateLimitException;
import com.githubactivity.core.exception.UserNotFoundException;
import com.githubactivity.core.model.Event;
import com.githubactivity.core.parser.EventParser;

public class GitHubServiceImpl implements GitHubService {
    
    @Override
    public List<Event> fetchEvents(String username) throws ApiException {
        String url = "https://api.github.com/users/" + username + "/events";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "github-activity-cli")
                .GET()
                .build();

        try {
            HttpResponse<String> response = 
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            int status = response.statusCode();
            
            if (status == 404) {
                throw new UserNotFoundException(username);
            } else if (status == 403) {
                throw new RateLimitException();
            } else if (status != 200) {
                throw new ApiException("Unexpected API error: " + status);
            }
            
            return EventParser.parse(response.body());
        } catch (IOException | InterruptedException e) {
            throw new ApiException("Network failure while reaching GitHub: " + e.getMessage());
        }
    }
}