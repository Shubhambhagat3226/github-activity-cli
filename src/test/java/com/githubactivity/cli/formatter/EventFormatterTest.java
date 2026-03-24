package com.githubactivity.cli.formatter;

import com.githubactivity.core.model.Event;
import com.githubactivity.core.model.Repo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventFormatterTest {

    @Test
    void shouldFormatEventsCorrectly() {

        Event event = new Event();
        event.setType("PushEvent");

        Repo repo = new Repo();
        repo.setName("test/repo");
        event.setRepo(repo);

        event.setCreated_at("2026-03-20T10:00:00Z");

        EventFormatter formatter = new EventFormatter();

        String output = formatter.formatEvents(List.of(event));

        assertTrue(output.contains("Pushed"));
        assertTrue(output.contains("test/repo"));
    }
}
