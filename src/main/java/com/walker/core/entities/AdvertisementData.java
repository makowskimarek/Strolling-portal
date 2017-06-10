package com.walker.core.entities;

/**
 * Created by Rafal on 09.06.2017.
 */
public class AdvertisementData {

    private int adId;
    private int userId;
    private LocationData location;
    private String description;
    private String strollStartTime;
    private String strollEndTime;
    private String adEndTime;
    private String privacy;

    public AdvertisementData() {
    }

    public AdvertisementData(int adId, int userId, LocationData location, String description, String strollStartTime, String strollEndTime, String adEndTime, String privacy) {
        this.adId = adId;
        this.userId = userId;
        this.location = location;
        this.description = description;
        this.strollStartTime = strollStartTime;
        this.strollEndTime = strollEndTime;
        this.adEndTime = adEndTime;
        this.privacy = privacy;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocationData getLocation() {
        return location;
    }

    public void setLocationId(LocationData location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrollStartTime() {
        return strollStartTime;
    }

    public void setStrollStartTime(String strollStartTime) {
        this.strollStartTime = strollStartTime;
    }

    public String getStrollEndTime() {
        return strollEndTime;
    }

    public void setStrollEndTime(String strollEndTime) {
        this.strollEndTime = strollEndTime;
    }

    public String getAdEndTime() {
        return adEndTime;
    }

    public void setAdEndTime(String adEndTime) {
        this.adEndTime = adEndTime;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
}
