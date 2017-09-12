package com.walker.rest.mvc;

import com.walker.core.entities.ListOfUserProfileData;
import com.walker.core.entities.SearchCriteria;
import com.walker.core.entities.UserProfileData;
import com.walker.core.services.SearchService;
import com.walker.core.services.impl.SearchServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rafal on 04.06.2017.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private SearchService service;

    public SearchController()
    {
        this.service = new SearchServiceImpl();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    List<UserProfileData> basicSearch(@RequestBody SearchCriteria searchCriteria)
    {
        ListOfUserProfileData list = service.searchUserProfileWithCriteria(searchCriteria, getCurrentUserId());
        return list.getUserProfileDataList();
    }

    private int getCurrentUserId()
    {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserIdFromNick(currentUser);
    }
}
