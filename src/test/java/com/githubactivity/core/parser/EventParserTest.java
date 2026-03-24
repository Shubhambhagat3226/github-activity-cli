package com.githubactivity.core.parser;

import java.util.List;

import com.githubactivity.core.parser.EventParser;

import com.githubactivity.core.exception.ApiException;
import com.githubactivity.core.model.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventParserTest {
    
    @Test
    void shouldParseValidJson() {
        EventParser parser = new EventParser();

        String json = """
                    [
                      {
                        "type": "PushEvent",
                        "created_at": "2025-01-01T10:00:00Z"
                      }
                    ]
                """;

        List<Event> events = parser.parse(json);

        assertEquals(1, events.size());
    }

    @Test
    void shouldThrowExceptionForEmptyJson() {
        EventParser parser = new EventParser();

        assertThrows(ApiException.class, () -> parser.parse(""));
    }

    @Test
    void shouldThrowExceptionForInvalidJson() {
        EventParser parser = new EventParser();

        String json = "invalid json";

        assertThrows(ApiException.class, () -> parser.parse(json));
    }
}
