package com.aidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
public class Location extends Identifiable {

    private String place;

    @Column(length = 50)
    private String country;

    @Column(length = 50)
    private String city;

    private long distance;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

}
