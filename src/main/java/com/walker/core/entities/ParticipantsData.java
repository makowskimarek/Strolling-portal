package com.walker.core.entities;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class ParticipantsData {
    private int user_id;
    private int stroll_id;
    private int rate;
    private String comment;

    public ParticipantsData(int user_id, int stroll_id, int rate, String comment) {
        this.user_id = user_id;
        this.stroll_id = stroll_id;
        this.rate = rate;
        this.comment = comment;
    }

    public ParticipantsData() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStroll_id() {
        return stroll_id;
    }

    public void setStroll_id(int stroll_id) {
        this.stroll_id = stroll_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
