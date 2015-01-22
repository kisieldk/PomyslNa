package com.example.damiank.pomyslna.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ulubione implements Serializable {

    public Integer id;
    public Integer recipeId;
    public Integer ownerId;

}
