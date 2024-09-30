package com.techelevator.model;

public class Eatery {
    private int id;
    private int zipCode;
    private String name;
    private String address;
    private String city;
    private String type;
    private String[] openTimes;
    private String[] closeTimes;
    private boolean hasTakeout;
    //greg thought this was an int
    private double rating;
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
