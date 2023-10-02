package com.example.kinoxp.Backend.enums;

import com.example.kinoxp.Backend.model.AgeRestrictions;

public enum AgeEnum {

    G("General audiences"),
    PG("Parental guidance"),
    PG13("Parents strongly cautioned"),
    R("Restricted"),
    NC17("Adults only");


    private final String description;

    AgeEnum(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }


}
