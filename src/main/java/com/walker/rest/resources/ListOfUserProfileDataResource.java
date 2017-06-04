package com.walker.rest.resources;

import com.walker.core.entities.ListOfUserProfileData;
import com.walker.core.entities.UserProfileData;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafal on 04.06.2017.
 */
public class ListOfUserProfileDataResource  extends ResourceSupport {
    private List<UserProfileData> userProfileDataList;

    public List<UserProfileData> getUserProfileDataList() {
        return userProfileDataList;
    }

    public void setUserProfileDataList(List<UserProfileData> userProfileDataList) {
        this.userProfileDataList = userProfileDataList;
    }

    public ListOfUserProfileData toListOfUserProfileData()
    {
        ListOfUserProfileData resoult =  new ListOfUserProfileData();

        resoult.setUserProfileDataList(this.userProfileDataList);
        return resoult;
    }
}
