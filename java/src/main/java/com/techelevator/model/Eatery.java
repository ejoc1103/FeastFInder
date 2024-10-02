package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Eatery {
    private String id;

    private String name;
    private String imageUrl;

    //Try changing this to a Json node
    private String address;

    private String category;

    private String openTime;

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    private String closeTime;

    private String hasTakeout;

    private String rating;

    private String phoneNumber;

    private String price;

    public Eatery(String id, String name, String imageUrl, String address, String category, String openTime, String closeTime, String hasTakeout, String rating, String phoneNumber, String price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.address = address;
        this.category = category;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.hasTakeout = hasTakeout;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHasTakeout() {
        return hasTakeout;
    }

    public void setHasTakeout(String hasTakeout) {
        this.hasTakeout = hasTakeout;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
