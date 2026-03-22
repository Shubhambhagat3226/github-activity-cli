package com.githubactivity.cli;

import java.util.List;

import com.githubactivity.cli.args.CliArguments;
import com.githubactivity.cli.args.CliArgumentsParser;
import com.githubactivity.cli.formatter.EventFormatter;
import com.githubactivity.core.exception.ApiException;
import com.githubactivity.core.exception.RateLimitException;
import com.githubactivity.core.exception.UserNotFoundException;
import com.githubactivity.core.model.Event;
import com.githubactivity.core.processor.EventProcessor;
import com.githubactivity.core.service.GitHubService;
import com.githubactivity.core.service.GitHubServiceImpl;

public class GitHubActivity {

    public static void main(String[] args) throws Exception {

        if (args.length == 0 || containsHelp(args)) {
            printHelp();
            return;
        }

        GitHubService service = new GitHubServiceImpl();
        EventFormatter formatter = new EventFormatter();
        CliArgumentsParser parser = new CliArgumentsParser();
        EventProcessor processor = new EventProcessor();

        CliArguments cliArgs = parser.parse(args);
        String username = cliArgs.getUsername();
        String type = cliArgs.getType();
        Integer limit = cliArgs.getLimit();

        try {

            List<Event> events = service.fetchEvents(username);

            events = processor.filterByType(events, type);
            events = processor.limit(events, limit);

            formatter.printEvents(events);

        } catch (IllegalArgumentException | UserNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (RateLimitException e) {
            System.out.println("⚠️ " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static boolean containsHelp(String[] args) {
        for (String arg : args) {
            if (arg.equals("--help"))
                return true;
        }
        return false;
    }

    private static void printHelp() {
        System.out.println("""
                Usage:
                  github-activity --user=<username> [options]

                Options:
                  --user=<username>     GitHub username (required)
                  --type=<eventType>    Filter by event type
                  --limit=<number>      Limit number of events
                  --help                Show this help message
                """);
    }

}