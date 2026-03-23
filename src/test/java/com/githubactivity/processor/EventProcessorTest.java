package com.githubactivity.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.githubactivity.core.processor.EventProcessor;
import org.junit.jupiter.api.Test;

import com.githubactivity.core.model.Event;

public class EventProcessorTest {

    private final EventProcessor processor = new EventProcessor();

    @Test
    void shouldFilterByType() {
        Event e1 = new Event();
        e1.setType("PushEvent");

        Event e2 = new Event();
        e2.setType("WatchEvent");

        List<Event> result = processor.filterByType(List.of(e1, e2), "PushEvent");

        assertEquals(1, result.size());
        assertEquals("PushEvent", result.get(0).getType());
    }

    @Test
    void shouldThrowExceptionForInvalidType() {
        Event e = new Event();
        e.setType("PushEvent");

        assertThrows(IllegalArgumentException.class, () -> processor.filterByType(List.of(e), "InvalidType"));
    }

    @Test
    void shouldLimitEvents() {
        Event e1 = new Event();
        Event e2 = new Event();

        List<Event> result = processor.limit(List.of(e1, e2), 1);

        assertEquals(1, result.size());
    }

    @Test
    void shouldThrowExceptionForInvalidLimit() {

        assertThrows(IllegalArgumentException.class, () -> processor.limit(List.of(new Event()), 0));
    }
}
