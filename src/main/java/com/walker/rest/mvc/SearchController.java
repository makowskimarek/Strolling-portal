package com.walker.rest.mvc;

import com.walker.core.entities.ListOfUserProfileData;
import com.walker.core.services.SearchService;
import com.walker.core.services.impl.SearchServiceImpl;
import com.walker.rest.resources.ListOfUserProfileDataResource;
import com.walker.rest.resources.SearchCriteriaResource;
import com.walker.rest.resources.asm.ListOfUserProfileDataResourceAsm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody ListOfUserProfileDataResource basicSearch(@RequestBody SearchCriteriaResource searchCriteriaResource)
    {
        ListOfUserProfileData list = service.searchUserProfileWithCriteria(searchCriteriaResource.toSearchCriteria());
        return new ListOfUserProfileDataResourceAsm().toResource(list);
    }
}
