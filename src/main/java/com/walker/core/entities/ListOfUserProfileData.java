package com.walker.core.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafal on 04.06.2017.
 */
public class ListOfUserProfileData {

    private List<UserProfileData> userProfileDataList;

    public ListOfUserProfileData()
    {
        userProfileDataList = new ArrayList<UserProfileData>();
    }

    public void add(UserProfileData userProfileData)
    {
        this.userProfileDataList.add(userProfileData);
    }

    public List<UserProfileData> getUserProfileDataList() {
        return userProfileDataList;
    }

    public void setUserProfileDataList(List<UserProfileData> userProfileDataList) {
        this.userProfileDataList = userProfileDataList;
    }
}
