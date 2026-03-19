package com.githubactivity.core.parser;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.githubactivity.core.model.Event;

public class EventParser {

    public static List<Event> parse(String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Event>>(){});
    }
}