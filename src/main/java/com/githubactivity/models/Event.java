package com.githubactivity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private String type;
    private Repo repo;
    private Payload payload;
    private String created_at;

    public Event() {}

    public String getType() {
        return this.type;
    }

    public Repo getRepo() {
        return this.repo;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}