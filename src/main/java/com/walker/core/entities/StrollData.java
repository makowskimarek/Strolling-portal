package com.walker.core.entities;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class StrollData {

    private int locationId;
    private String info;
    private String data_start;
    private String data_end;
    private String status;
    private int ad_id;
    private String privacy;
    private int[] users;

    public StrollData() {
    }

    public StrollData(int locationId, String info, String data_start, String data_end, String status, int ad_id, String privacy, int[] users) {
        this.locationId = locationId;
        this.info = info;
        this.data_start = data_start;
        this.data_end = data_end;
        this.status = status;
        this.ad_id = ad_id;
        this.privacy = privacy;
        this.users = users;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData_start() {
        return data_start;
    }

    public void setData_start(String data_start) {
        this.data_start = data_start;
    }

    public String getData_end() {
        return data_end;
    }

    public void setData_end(String data_end) {
        this.data_end = data_end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public int[] getUsers() {
        return users;
    }

    public void setUsers(int[] users) {
        this.users = users;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
