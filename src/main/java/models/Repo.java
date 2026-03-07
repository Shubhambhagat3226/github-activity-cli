package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {
    
    private String name;

    public Repo() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}