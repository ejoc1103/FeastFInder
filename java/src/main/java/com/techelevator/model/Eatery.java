package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Eatery {
    @JsonProperty("id")
    private String id;
    @JsonProperty("zip_code")
    private String zipCode;
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
    private String hasTakeout;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("phone_number")
    private String phoneNumber;

    public int getId() {
        return Integer.parseInt(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getZipCode() {
        return Integer.parseInt(zipCode);
    }

    public void setZipCode(String zipCode) {
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
        return Boolean.parseBoolean(hasTakeout);
    }

    public void setHasTakeout(String hasTakeout) {
        this.hasTakeout = hasTakeout;
    }

    public double getRating() {
        return Double.parseDouble(rating);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPhoneNumber() {
        return Integer.parseInt(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
