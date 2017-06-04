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
                    calculateRange(latitude, longtitude, criteriaData.getLatitude(), criteriaData.getLongtitude())));
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

    public int getNearbyUsersLimit() {
        return nearbyUsersLimit;
    }
}
