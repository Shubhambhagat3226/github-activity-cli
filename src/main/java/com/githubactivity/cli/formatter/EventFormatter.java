package com.githubactivity.cli.formatter;

import com.githubactivity.core.model.Event;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class EventFormatter {

    public String formatEvents(List<Event> events) {

        if (events == null || events.isEmpty()) {
            return "No activity found.";
        }

        StringBuilder output = new StringBuilder();

        output.append("GitHub Activity");
        output.append("----------------------\n");

        for (Event event : events) {

            String repo = event.getRepo().getName();
            String type = event.getType();
            String timeAgo = getTimeAgo(event.getCreated_at());

            switch (type) {

                case "PushEvent":
                    Integer commits = null;

                    if (event.getPayload() != null && event.getPayload().getSize() > 0) {
                        commits = event.getPayload().getSize();
                    }

                    if (commits != null) {
                        output.append("- ").append(timeAgo)
                                .append(" -> Pushed ").append(commits)
                                .append(" commits to ").append(repo);
                    } else {
                        output.append("- ").append(timeAgo)
                                .append(" -> Pushed commits to ").append(repo);
                    }
                    break;

                case "WatchEvent":
                    output.append("- ").append(timeAgo)
                            .append(" -> Starred ").append(repo);
                    break;

                case "CreateEvent":
                    output.append("- ").append(timeAgo)
                            .append(" -> Created repository or branch in ").append(repo);
                    break;

                default:
                    String readableType = event.getType()
                            .replace("Event", "")
                            .replaceAll("([A-Z])", " $1")
                            .trim();

                    output.append("- ").append(timeAgo)
                            .append(" -> ").append(readableType)
                            .append(" on ").append(repo);
            }
        }

        return output.toString();
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