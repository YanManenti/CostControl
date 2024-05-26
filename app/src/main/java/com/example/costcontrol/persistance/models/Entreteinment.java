package com.example.costcontrol.persistance.models;

import java.math.BigDecimal;

public class Entreteinment {
    private Integer id;
    private String name;
    private Float price;
    private Integer tripId;

    public Entreteinment() {
    }

    public Entreteinment(Integer id, String name, Float price, Integer tripId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tripId = tripId;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }
}
