package com.githubactivity.cli;

import java.util.List;

import com.githubactivity.cli.formatter.EventFormatter;
import com.githubactivity.core.exception.ApiException;
import com.githubactivity.core.exception.RateLimitException;
import com.githubactivity.core.exception.UserNotFoundException;
import com.githubactivity.core.model.Event;
import com.githubactivity.core.service.GitHubService;
import com.githubactivity.core.service.GitHubServiceImpl;

public class GitubActivity {

    private static final GitHubService service = new GitHubServiceImpl();
    private static final EventFormatter formatter = new EventFormatter();

    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            System.out.println("Usage: java -cp \".:lib/*:src/main/java\" GithubActivity <username>");
            return;
        }

        String username = args[0];

        try {
           
            List<Event> events = service.fetchEvents(username);
            formatter.printEvents(events);

        } catch (UserNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (RateLimitException e) {
            System.out.println("⚠️ " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}