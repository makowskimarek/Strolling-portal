package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlUser;
import com.walker.core.entities.ListOfUserProfileData;
import com.walker.core.entities.SearchCriteria;
import com.walker.core.entities.UserRange;
import com.walker.core.services.SearchService;

import java.util.List;

/**
 * Created by Rafal on 04.06.2017.
 */
public class SearchServiceImpl implements SearchService {

    private ControlUser controlUser;

    public SearchServiceImpl()
    {
        controlUser = new ControlUser();
    }

    @Override
    public ListOfUserProfileData searchUserProfileWithCriteria(SearchCriteria searchCriteria, int userId) {

        List<UserRange> usersRange = controlUser.getUsersByCriteries(searchCriteria.getUserLatitude(), searchCriteria.getUserLongtitude(),
                searchCriteria.getDistance(), searchCriteria.getAgeFrom(), searchCriteria.getAgeTo(), userId);

        ListOfUserProfileData list = new ListOfUserProfileData();
        for (UserRange userRange : usersRange) {
            list.add(controlUser.getUserProfileData(userRange.getUserId()));
        }
        return list;
    }

    @Override
    public int getUserIdFromNick(String nick) {
        return controlUser.getUserID(nick);
    }
}

