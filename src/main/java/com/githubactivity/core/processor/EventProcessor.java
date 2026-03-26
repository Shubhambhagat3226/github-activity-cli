package com.githubactivity.core.processor;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.githubactivity.core.model.DisplayEvent;
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

    public List<DisplayEvent> toDisplayEvents(List<Event> events) {

        return  events.stream().map(event -> {
            String repo = event.getRepo().getName();
            String type = event.getType();
            String timeAgo = getTimeAgo(event.getCreated_at());

            String message = null;

            EventType eventType = EventType.from(event.getType());

            switch (eventType) {
                case PUSH:
                    Integer commits = null;

                    if (event.getPayload() != null && event.getPayload().getSize() > 0) {
                        commits = event.getPayload().getSize();
                    }

                    if (commits != null) {
                        message = "Pushed " + commits + " commits to " + repo;
                    } else {
                        message = "Pushed commits to " + repo;
                    }
                    break;

                case WATCH:
                    message = "Starred " + repo;
                    break;

                case CREATE:
                    message = "Created repository or branch in " + repo;
                    break;

                case UNKNOWN:
                    String readableType = type
                            .replace("Event", "")
                            .replaceAll("([A-Z])", " $1")
                            .trim();

                    message = readableType + " on " + repo;
            }

            return new DisplayEvent(message, timeAgo);

        }).toList();
    }

    private static String getTimeAgo(String timestamp) {

        Instant eventTime = Instant.parse(timestamp);
        Instant now = Instant.now();

        Duration duration = Duration.between(eventTime, now);

        long hours = duration.toHours();
        long days = duration.toDays();
        long minutes = duration.toMinutes();

        if (days > 0) {
            return days == 1 ? "1 day ago" : days + " days ago";
        }

        if (hours > 0) {
            return hours == 1 ? "1 hour ago" : hours + " hours ago";
        }

        if (minutes > 0) {
            return minutes == 1 ? "1 minute ago" : minutes + " minutes ago";
        }

        return "just now";
    }
}
