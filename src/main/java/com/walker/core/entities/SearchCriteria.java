package com.walker.core.entities;

/**
 * Created by Rafal on 25.04.2017.
 */

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 * @author pankaj
 */
public class SearchCriteria {
        int userId;
        int ageFrom;
        int ageTo;
        double distance;
        double userLatitude;
        double userLongtitude;

    public SearchCriteria() {

    }

    public SearchCriteria(int userId, int ageFrom, int ageTo, double distance, double userLatitude, double userLongtitude) {
            this.userId = userId;
            this.ageFrom = ageFrom;
            this.ageTo = ageTo;
            this.distance = distance;
            this.userLatitude = userLatitude;
            this.userLongtitude = userLongtitude;
        }

        public double getUserLatitude() {
            return userLatitude;
        }

        public void setUserLatitude(double userLatitude) {
            this.userLatitude = userLatitude;
        }

        public double getUserLongtitude() {
            return userLongtitude;
        }

        public void setUserLongtitude(double userLongtitude) {
            this.userLongtitude = userLongtitude;
        }

        public int getAgeFrom() {
            return ageFrom;
        }

        public void setAgeFrom(int ageFrom) {
            this.ageFrom = ageFrom;
        }

        public int getAgeTo() {
            return ageTo;
        }

        public void setAgeTo(int ageTo) {
            this.ageTo = ageTo;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getUserId() {

            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }