package com.githubactivity.cli;

import com.githubactivity.cli.args.CliArgumentsParser;
import com.githubactivity.cli.formatter.EventFormatter;
import com.githubactivity.core.service.FakeGitHubService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GitHubActivityTest {
    @Test
    void shouldRunWithFakeService() {

        FakeGitHubService fakeGitHubService = new FakeGitHubService();
        fakeGitHubService.setEvents(List.of(
                FakeGitHubService.createEvent(
                        "PushEvent",
                        "test/repo",
                        "2026-03-20T10:00:00Z"
                )
        ));

        EventFormatter formatter = new EventFormatter();
        CliArgumentsParser cliArgumentsParser = new CliArgumentsParser();

        GitHubActivity app = new GitHubActivity(fakeGitHubService, formatter, cliArgumentsParser);

        String[] args = {"--user=test"};

        assertDoesNotThrow(() -> app.run(args));
    }

    @Test
    void shouldHandleEmptyEvents() {

        FakeGitHubService fakeService = new FakeGitHubService();
        fakeService.setEvents(List.of());

        GitHubActivity app = new GitHubActivity(
                fakeService,
                new EventFormatter(),
                new CliArgumentsParser()
        );

        assertDoesNotThrow(() -> app.run(new String[]{"--user=test"}));
    }

    @Test
    void shouldHandleApiError() {

        FakeGitHubService fakeService = new FakeGitHubService();
        fakeService.setThrowError(true);

        GitHubActivity app = new GitHubActivity(
                fakeService,
                new EventFormatter(),
                new CliArgumentsParser()
        );

        assertDoesNotThrow(() -> app.run(new String[]{"--user=test"}));
    }
}
