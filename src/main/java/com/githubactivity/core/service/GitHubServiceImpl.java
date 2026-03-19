package com.githubactivity.core.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.githubactivity.core.model.Event;
import com.githubactivity.core.parser.EventParser;

public class GitHubServiceImpl implements GitHubService {
    public List<Event> fetchEvents(String username) throws Exception {
        String url = "https://api.github.com/users/" + username + "/events";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "github-activity-cli")
                .GET()
                .build();
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
         if (response.statusCode() != 200) {
            throw new RuntimeException("User not found or API error");
        }
        return EventParser.parse(response.body());
    }
}