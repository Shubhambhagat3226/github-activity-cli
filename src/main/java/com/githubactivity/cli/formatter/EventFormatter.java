package com.githubactivity.cli.formatter;

import com.githubactivity.core.model.DisplayEvent;

import java.util.List;

public class EventFormatter {

    public String formatEvents(List<DisplayEvent> events) {

        if (events == null || events.isEmpty()) {
            return "No activity found.";
        }

        StringBuilder output = new StringBuilder();

        output.append("GitHub Activity\n");
        output.append("----------------------\n");

        for (DisplayEvent event : events) {
            output.append("- ")
                    .append(event.getTimeAgo())
                    .append(" -> ")
                    .append(event.getMessage())
                    .append("\n");
        }

        return output.toString();
    }

}