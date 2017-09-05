package com.aidar.model;

import com.aidar.dto.IdentifiableJsonSerializer;
import com.aidar.dto.VisitDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
public class Visit extends Identifiable {

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;

    @Column(name = "visited_at")
    private Timestamp visitedAt;

    private byte mark;

    public Visit() {
    }

    public Visit(VisitDto visit, Location location, User user) {
        id = visit.getId();
        this.location = location;
        this.user = user;
        visitedAt = visit.getVisitedAt();
        mark = visit.getMark();
    }

    @JsonSerialize(using = IdentifiableJsonSerializer.class)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonSerialize(using = IdentifiableJsonSerializer.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("visited_at")
    public Timestamp getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Timestamp visitedAt) {
        this.visitedAt = visitedAt;
    }

    public byte getMark() {
        return mark;
    }

    public void setMark(byte mark) {
        this.mark = mark;
    }

}
