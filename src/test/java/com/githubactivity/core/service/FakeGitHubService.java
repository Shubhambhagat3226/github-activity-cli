package com.githubactivity.core.service;

import com.githubactivity.core.exception.ApiException;
import com.githubactivity.core.model.Event;
import com.githubactivity.core.model.Repo;

import java.util.ArrayList;
import java.util.List;

public class FakeGitHubService implements GitHubService{

    private List<Event> events = new ArrayList<>();
    private boolean throwError = false;

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setThrowError(boolean throwError) {
        this.throwError = throwError;
    }

    @Override
    public List<Event> fetchEvents(String username) throws ApiException {
        if (throwError) {
            throw new ApiException("Fake API Error");
        }
        return events;
    }

    public static Event createEvent(String type, String repoName, String time) {
        Event event = new Event();
        event.setType(type);

        Repo repo = new Repo();
        repo.setName(repoName);
        event.setRepo(repo);

        event.setCreated_at(time);
        return event;
    }
}
