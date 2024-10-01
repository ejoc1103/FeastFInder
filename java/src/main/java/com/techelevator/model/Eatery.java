package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Eatery {
    @JsonProperty("id")
    private int id;
    @JsonProperty("zip_code")
    private int zipCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("type")
    private String type;
    @JsonProperty("open_times")
    private String[] openTimes;
    @JsonProperty("close_times")
    private String[] closeTimes;
    @JsonProperty("has_takeout")
    private boolean hasTakeout;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("phone_number")
    private int phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getOpenTimes() {
        return openTimes;
    }

    public void setOpenTimes(String[] openTimes) {
        this.openTimes = openTimes;
    }

    public String[] getCloseTimes() {
        return closeTimes;
    }

    public void setCloseTimes(String[] closeTimes) {
        this.closeTimes = closeTimes;
    }

    public boolean isHasTakeout() {
        return hasTakeout;
    }

    public void setHasTakeout(boolean hasTakeout) {
        this.hasTakeout = hasTakeout;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
