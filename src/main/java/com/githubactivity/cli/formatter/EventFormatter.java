package com.githubactivity.cli.formatter;

import com.githubactivity.core.model.Event;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class EventFormatter {

    public void printEvents(List<Event> events) {

        if (events == null || events.isEmpty()) {
            System.out.println("No activity found.");
            return;
        }

        System.out.println("GitHub Activity");
        System.out.println("----------------------");

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
                        System.out.println(
                                "- " + timeAgo + " -> Pushed " + commits + " commits to " + repo);
                    } else {
                        System.out.println(
                                "- " + timeAgo + " -> Pushed commits to " + repo);
                    }
                    break;

                case "WatchEvent":
                    System.out.println(
                            "- " + timeAgo + " -> Starred " + repo);
                    break;

                case "CreateEvent":
                    System.out.println(
                            "- " + timeAgo + " -> Created repository or branch in " + repo);
                    break;

                default:
                    String readableType = event.getType()
                            .replace("Event", "")
                            .replaceAll("([A-Z])", " $1")
                            .trim();

                    System.out.println("- " + timeAgo + " -> "
                            + readableType + " on " + repo);
            }
        }
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