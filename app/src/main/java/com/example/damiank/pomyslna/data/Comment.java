package com.example.damiank.pomyslna.data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    @JsonProperty("record")
    public List<Komentarz> records = new ArrayList<Komentarz>();
}
