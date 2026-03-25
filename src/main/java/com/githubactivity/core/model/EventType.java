package com.githubactivity.core.model;

public enum EventType {
    PUSH,
    WATCH,
    CREATE,
    UNKNOWN;

    public static EventType from(String type) {
        if (type == null) return UNKNOWN;

        return switch (type) {
            case "PushEvent" -> PUSH;
            case "WatchEvent" -> WATCH;
            case "CreateEvent" -> CREATE;
            default -> UNKNOWN;
        };
    }
}
