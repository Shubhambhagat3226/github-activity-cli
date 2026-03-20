package com.githubactivity.core.exception;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(String username) {
        super("User not found: " + username);
    }
}
