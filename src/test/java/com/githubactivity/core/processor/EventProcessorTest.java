package com.githubactivity.core.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.githubactivity.core.model.Event;

public class EventProcessorTest {

    @Test
    void shouldFilterByType() {
        EventProcessor processor = new EventProcessor();

        Event e1 = new Event();
        e1.setType("PushEvent");

        Event e2 = new Event();
        e2.setType("WatchEvent");

        List<Event> events = List.of(e1, e2);

        List<Event> result = processor.filterByType(events, "PushEvent");

        assertEquals(1, result.size());
    }

    @Test
    void shouldThrowExceptionForInvalidType() {
        EventProcessor processor = new EventProcessor();

        Event e = new Event();
        e.setType("PushEvent");

        List<Event> events = List.of(e);

        assertThrows(IllegalArgumentException.class, () -> processor.filterByType(events, "InvalidType"));
    }

    @Test
    void shouldLimitEvents() {
        EventProcessor processor = new EventProcessor();

        Event e1 = new Event();
        Event e2 = new Event();

        List<Event> events = List.of(e1, e2);

        List<Event> result = processor.limit(events, 1);

        assertEquals(1, result.size());
    }

    @Test
    void shouldThrowExceptionForInvalidLimit() {
        EventProcessor processor = new EventProcessor();

        List<Event> events = List.of(new Event());

        assertThrows(IllegalArgumentException.class, () -> processor.limit(events, 0));
    }
}
