package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Event;

public class EventParser {

    public static Event[] parse(String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Event[].class);
    }
}