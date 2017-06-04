package com.walker.core.entities;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class UserRange {
    private int userId;
    private double range;



    public UserRange(int userId, double range) {
        this.userId = userId;
        this.range = range;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }
}
