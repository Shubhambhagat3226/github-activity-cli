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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GitHubServiceImpl implements GitHubService {

    private static final Logger log = LoggerFactory.getLogger(GitHubServiceImpl.class);

    @Override
    public List<Event> fetchEvents(String username) throws ApiException {
        String url = "https://api.github.com/users/" + username + "/events";

        log.debug("Target URL: {}", url);

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
                log.error("User not found: {}", username);
                throw new UserNotFoundException(username);
            } else if (status == 403) {
                log.error("GitHub API rate limit exceeded.");
                throw new RateLimitException();
            } else if (status != 200) {
                log.error("GitHub API returned unexpected status: {}", status);
                throw new ApiException("Unexpected API error: " + status);
            }
            
            return new EventParser().parse(response.body());
        } catch (IOException | InterruptedException e) {
            log.error("Network failure: {}", e.getMessage());
            throw new ApiException("Network failure while reaching GitHub: " + e.getMessage());
        }
    }
}