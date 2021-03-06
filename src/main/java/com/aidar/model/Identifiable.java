package com.aidar.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Aidar Shaifutdinov.
 */
@MappedSuperclass
public abstract class Identifiable {

    @Id
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
