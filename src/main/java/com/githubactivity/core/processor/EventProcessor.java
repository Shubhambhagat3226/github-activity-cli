package com.githubactivity.core.processor;

import java.util.Arrays;
import java.util.List;

import com.githubactivity.core.model.Event;
import com.githubactivity.core.model.EventType;

public class EventProcessor {
    public List<Event> filterByType(List<Event> events, String type) {
        if (type == null) return events;

        boolean isValid = Arrays.stream(EventType.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(type));

        if (!isValid) {
            throw new IllegalArgumentException("Invalid event type: " + type);
        }

        return events.stream()
                .filter(e -> type.equalsIgnoreCase(e.getType()))
                .toList();
    }

    public List<Event> limit(List<Event> events, Integer limit) {
        if (limit == null) return events;

        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");          
        }

        return events.stream()
                .limit(limit)
                .toList();
    }
}
