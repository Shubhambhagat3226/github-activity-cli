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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GitHubActivity {

    private final GitHubService service;
    private final EventFormatter formatter;
    private final CliArgumentsParser parser;
    private static final Logger log = LoggerFactory.getLogger(GitHubActivity.class);

    public GitHubActivity(GitHubService service,
                          EventFormatter formatter,
                          CliArgumentsParser parser) {
        this.service = service;
        this.formatter = formatter;
        this.parser = parser;
    }

    public static void main(String[] args) throws Exception {

        GitHubService service = new GitHubServiceImpl();
        EventFormatter formatter = new EventFormatter();
        CliArgumentsParser parser = new CliArgumentsParser();

        GitHubActivity app = new GitHubActivity(service, formatter, parser);
        app.run(args);
    }

    public void run(String[] args) {
        log.info("Application started with args: {}", String.join(" ", args));

        if (args.length == 0 || containsHelp(args)) {
            log.debug("No arguments or --help detected. Showing help menu.");
            printHelp();
            return;
        }

        EventProcessor processor = new EventProcessor();

        try {
            CliArguments cliArgs = parser.parse(args);

            log.info("Fetching events for user: {}",
                    cliArgs.getUsername());
            List<Event> events = service.fetchEvents(cliArgs.getUsername());

            events = processor.filterByType(events, cliArgs.getType());
            events = processor.limit(events, cliArgs.getLimit());

            String result = formatter.formatEvents(events);
            System.out.println(result);

        } catch (UserNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (RateLimitException e) {
            System.out.println("⚠️ " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {}", e.getMessage());
            System.out.println("Error: " + e.getMessage());
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
                  github-activity.bat --user=<username> [options]

                Options:
                  --user=<username>     GitHub username (required)
                  --type=<eventType>    Filter by event type
                  --limit=<number>      Limit number of events
                  --help                Show this help message
                """);
    }

}