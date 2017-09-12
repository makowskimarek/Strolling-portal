package com.walker.core.services;

import com.walker.core.entities.ListOfUserProfileData;
import com.walker.core.entities.SearchCriteria;

/**
 * Created by Rafal on 04.06.2017.
 */
public interface SearchService {
    public ListOfUserProfileData searchUserProfileWithCriteria(SearchCriteria searchCriteria, int userId);
    public int getUserIdFromNick(String nick);
}
