package com.walker.core.entities;

/**
 * @author Marek Makowski
 * @version 1.0
 */

public class InvitationData {

    private int currentUserId;
    private int userId;
    private String date;
    private String time;
    private String locationName;
    private double longtitude;
    private double latitude;

    public InvitationData(int currentUserId, int userId, String date, String time, String locationName, double longtitude, double latitude) {
        this.currentUserId = currentUserId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.locationName = locationName;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
