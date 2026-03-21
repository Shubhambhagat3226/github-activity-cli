package com.githubactivity.core.parser;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubactivity.core.exception.ApiException;
import com.githubactivity.core.model.Event;

public class EventParser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<Event> parse(String json) throws ApiException {

        if (json == null || json.isBlank()) {
            throw new ApiException("Received empty response from GitHub");
        }

        try {
            return MAPPER.readValue(json, new TypeReference<List<Event>>(){});
        } catch (JsonProcessingException e) {
            throw new ApiException("Failed to parse response");
        }
    }
}