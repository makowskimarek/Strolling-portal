package com.walker.DataBase;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class CriteriaData {
    private int user_id;
    private String birth_date;
    private double latitude;
    private double longtitude;


    public CriteriaData(int user_id, String birth_date, double latitude, double longtitude) {
        this.user_id = user_id;
        this.birth_date = birth_date;
        this.latitude = latitude;
        this.longtitude = longtitude;

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

}
