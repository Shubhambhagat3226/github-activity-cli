package com.githubactivity;

import com.githubactivity.formatter.EventFormatter;
import com.githubactivity.models.Event;
import com.githubactivity.parser.EventParser;
import com.githubactivity.service.GithubService;

public class GithubActivity {

    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            System.out.println("Usage: java -cp \".:lib/*:src/main/java\" GithubActivity <username>");
            return;
        }

        String username = args[0];

        try {

            String json = GithubService.fetchEvents(username);
            Event[] events = EventParser.parse(json);
            EventFormatter.printEvents(events);

        } catch (Exception e) {
            System.out.println("Error fetching activity: " + e.getMessage());
        }
    }
}