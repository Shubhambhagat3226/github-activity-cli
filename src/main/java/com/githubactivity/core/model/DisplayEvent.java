package com.githubactivity.core.model;

public class DisplayEvent {

    private String message;
    private String timeAgo;

    public DisplayEvent(String message, String timeAgo) {
        this.message = message;
        this.timeAgo = timeAgo;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeAgo() {
        return timeAgo;
    }
}