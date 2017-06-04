package com.walker.rest.resources;

import com.walker.core.entities.SearchCriteria;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.access.method.P;

/**
 * Created by Rafal on 04.06.2017.
 */
public class SearchCriteriaResource extends ResourceSupport {
    int userId;
    int ageFrom;
    int ageTo;
    Double distance;
    Double userLatitude;
    Double userLongtitude;

    public SearchCriteriaResource()
    {

    }

    public SearchCriteriaResource(int userId, int ageFrom, int ageTo, double distance, Double userLatitude, Double userLongtitude) {
        this.userId = userId;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.distance = distance;
        this.userLatitude = userLatitude;
        this.userLongtitude = userLongtitude;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongtitude() {
        return userLongtitude;
    }

    public void setUserLongtitude(Double userLongtitude) {
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

    public SearchCriteria toSearchCriteria()
    {
        return new SearchCriteria(this.userId,
                this.ageFrom,
                this.ageTo,
                this.distance,
                this.userLatitude,
                this.userLongtitude
                );
    }
}
