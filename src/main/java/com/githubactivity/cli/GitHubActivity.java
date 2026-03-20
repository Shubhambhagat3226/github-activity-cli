package com.githubactivity.cli;

import java.util.List;

import com.githubactivity.cli.args.CliArguments;
import com.githubactivity.cli.args.CliArgumentsParser;
import com.githubactivity.cli.formatter.EventFormatter;
import com.githubactivity.core.exception.ApiException;
import com.githubactivity.core.exception.RateLimitException;
import com.githubactivity.core.exception.UserNotFoundException;
import com.githubactivity.core.model.Event;
import com.githubactivity.core.service.GitHubService;
import com.githubactivity.core.service.GitHubServiceImpl;

public class GitHubActivity {

    
    public static void main(String[] args) throws Exception{
        
        GitHubService service = new GitHubServiceImpl();
        EventFormatter formatter = new EventFormatter();
        CliArgumentsParser parser = new CliArgumentsParser();

        CliArguments cliArgs = parser.parse(args);
        String username = cliArgs.getUsername();
        String type = cliArgs.getType();
        Integer limit = cliArgs.getLimit();

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