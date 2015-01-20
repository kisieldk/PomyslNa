package com.example.damiank.pomyslna.data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements Serializable {

    public String email;
    public String first_name;
    public String last_name;
    public  String new_password;

    @JsonProperty("session_id")
    public String sessionId;

}