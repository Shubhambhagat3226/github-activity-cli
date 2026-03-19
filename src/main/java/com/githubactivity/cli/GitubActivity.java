package com.githubactivity.cli;

import java.util.List;

import com.githubactivity.cli.formatter.EventFormatter;
import com.githubactivity.core.model.Event;
import com.githubactivity.core.service.GitHubService;
import com.githubactivity.core.service.GitHubServiceImpl;

public class GitubActivity {

    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            System.out.println("Usage: java -cp \".:lib/*:src/main/java\" GithubActivity <username>");
            return;
        }

        String username = args[0];

        try {

            GitHubService service = new GitHubServiceImpl();
            List<Event> events = service.fetchEvents(username);
            EventFormatter.printEvents(events);

        } catch (Exception e) {
            System.out.println("Error fetching activity: " + e.getMessage());
        }
    }
}