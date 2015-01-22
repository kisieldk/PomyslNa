package com.example.damiank.pomyslna.data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Serializable{
    public Integer id;
   public Integer ownerId;
    public String title;
    public String introduction;
   public String ingredients;
   public String steps;
  public String created;
    public Integer preparationMinutes;
    public Integer cookingMinutes;
            public Integer servings;

    @JsonIgnore
    public String pictureBytes;

}
