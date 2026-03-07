package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {

    private int size;

    public Payload() {}

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}