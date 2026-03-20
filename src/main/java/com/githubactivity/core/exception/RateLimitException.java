package com.githubactivity.core.exception;

public class RateLimitException extends ApiException {
    public RateLimitException() {
        super("GitHub API rate limit exceeded");
    }
}
