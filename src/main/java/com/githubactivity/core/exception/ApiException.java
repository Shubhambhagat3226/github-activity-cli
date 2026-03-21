package com.githubactivity.core.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}