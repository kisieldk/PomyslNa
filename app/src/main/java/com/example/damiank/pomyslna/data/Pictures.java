package com.example.damiank.pomyslna.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Pictures {
    public Integer id;
    public Integer ownerId;
    public String base64bytes;
}
