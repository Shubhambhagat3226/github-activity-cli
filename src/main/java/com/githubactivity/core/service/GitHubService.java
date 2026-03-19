package com.githubactivity.core.service;

import java.util.List;

import com.githubactivity.core.model.Event;

public interface GitHubService {
    List<Event> fetchEvents(String username) throws Exception;
}
