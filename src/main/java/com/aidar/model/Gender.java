package com.aidar.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Aidar Shaifutdinov.
 */
public enum Gender {

    MALE("m"),
    FEMALE("f");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public Gender of(String value) {
        return MALE.value.equals(value) ? MALE : FEMALE;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
