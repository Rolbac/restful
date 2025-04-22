package com.quique.demoRestful.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "REGIONS")
public class Region {
    @Id
    @Column(name = "REGION_ID")
    private Integer id;

    @Column(name = "REGION_NAME")
    private String name;

    public Region() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}