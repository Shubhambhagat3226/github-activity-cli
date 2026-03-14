package com.githubactivity.formatter;

import com.githubactivity.models.Event;

import java.time.Duration;
import java.time.Instant;

public class EventFormatter {

    public static void printEvents(Event[] events) {

        for (Event event : events) {

            String repo = event.getRepo().getName();
            String type = event.getType();

            String timeAgo = getTimeAgo(event.getCreated_at());

            switch (type) {

                case "PushEvent":
                    int commits = 0;

                    if (event.getPayload() != null) {
                        commits = event.getPayload().getSize();
                    }

                    System.out.println(
                            "- " + timeAgo + " → Pushed " + commits + " commits to " + repo
                    );
                    break;

                case "WatchEvent":
                    System.out.println(
                            "- " + timeAgo + " → Starred " + repo
                    );
                    break;

                case "CreateEvent":
                    System.out.println(
                            "- " + timeAgo + " → Created repository or branch in " + repo
                    );
                    break;

                default:
                    System.out.println(
                            "- " + timeAgo + " → Performed " + type + " on " + repo
                    );
            }
        }
    }

    private static String getTimeAgo(String timestamp) {

        Instant eventTime = Instant.parse(timestamp);
        Instant now = Instant.now();

        Duration duration = Duration.between(eventTime, now);

        long hours = duration.toHours();
        long days = duration.toDays();

        if (days > 0) {
            return days + " days ago";
        }

        if (hours > 0) {
            return hours + " hours ago";
        }

        return "just now";
    }
}