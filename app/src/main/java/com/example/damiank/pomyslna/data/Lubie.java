package com.example.damiank.pomyslna.data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lubie implements Serializable{

    @JsonProperty("record")
    public List<Recipe> records = new ArrayList<Recipe>();
}
