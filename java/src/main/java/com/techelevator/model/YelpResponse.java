package com.techelevator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YelpResponse {
    @JsonProperty("businesses")
    private List<Eatery> businesses;

    public List<Eatery> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Eatery> businesses) {
        this.businesses = businesses;
    }
}