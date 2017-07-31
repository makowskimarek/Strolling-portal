package com.walker.core.entities;

/**
 * Created by Rafal on 29.07.2017.
 */
public class ProfileData {
    private int userId;
    private int photoId;
    private String description;
    private int locationId;

    public ProfileData() {
    }

    public ProfileData(int userId, int photoId, String description, int locationId) {
        this.userId = userId;
        this.photoId = photoId;
        this.description = description;
        this.locationId = locationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
