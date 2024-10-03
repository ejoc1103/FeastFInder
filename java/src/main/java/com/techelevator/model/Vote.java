package com.techelevator.model;

import java.sql.Date;

public class Vote {
    private int vote_id;
    private String vote_name;
    private String vote_description;
    private Date vote_start_date;
    private Date vote_end_date;
    private int eatery_id;
    private boolean is_active;
    private int user_vote_id;

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public String getVote_name() {
        return vote_name;
    }

    public void setVote_name(String vote_name) {
        this.vote_name = vote_name;
    }

    public String getVote_description() {
        return vote_description;
    }

    public void setVote_description(String vote_description) {
        this.vote_description = vote_description;
    }

    public Date getVote_start_date() {
        return vote_start_date;
    }

    public void setVote_start_date(Date vote_start_date) {
        this.vote_start_date = vote_start_date;
    }

    public Date getVote_end_date() {
        return vote_end_date;
    }

    public void setVote_end_date(Date vote_end_date) {
        this.vote_end_date = vote_end_date;
    }

    public int getEatery_id() {
        return eatery_id;
    }

    public void setEatery_id(int eatery_id) {
        this.eatery_id = eatery_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getUser_vote_id() {
        return user_vote_id;
    }

    public void setUser_vote_id(int user_vote_id) {
        this.user_vote_id = user_vote_id;
    }

}
