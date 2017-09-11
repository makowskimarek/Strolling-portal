package com.walker.model;

import com.walker.DataBase.CriteriaData;
import com.walker.core.entities.UserRange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class Model {


    private int nearbyUsersLimit = 10;

    private double calculateRange(double latitude1, double longtitude1, double latitude2, double longtitude2) {
        return Math.sqrt(
                Math.pow(Math.abs(latitude1) - Math.abs(latitude2), 2.d)
                        + Math.pow(Math.abs(longtitude1) - Math.abs(longtitude2), 2.d)
        );
    }

    public List<UserRange> getNearbyUsers(List<CriteriaData> usersLocationList, double latitude, double longtitude, double range) {

        List<UserRange> userRangeList = new ArrayList<>();

        for (CriteriaData criteriaData : usersLocationList) {
            userRangeList.add(new UserRange(criteriaData.getUser_id(),
                    distance(latitude, longtitude, criteriaData.getLatitude(), criteriaData.getLongtitude(), "K")));
        }

        for(int i = 0; i < userRangeList.size();i++){
            if(userRangeList.get(i).getRange()>range){
                userRangeList.remove(i);
                i--;
            }
        }
        userRangeList.sort(Comparator.comparing(UserRange::getRange));

        return userRangeList;
    }


    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    public int getNearbyUsersLimit() {
        return nearbyUsersLimit;
    }
}
