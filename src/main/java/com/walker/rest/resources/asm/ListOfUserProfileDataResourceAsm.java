package com.walker.rest.resources.asm;

import com.walker.core.entities.ListOfUserProfileData;
import com.walker.rest.mvc.SearchController;
import com.walker.rest.resources.ListOfUserProfileDataResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 04.06.2017.
 */
public class ListOfUserProfileDataResourceAsm extends ResourceAssemblerSupport<ListOfUserProfileData, ListOfUserProfileDataResource> {

    public ListOfUserProfileDataResourceAsm()
    {
        super(SearchController.class, ListOfUserProfileDataResource.class);
    }

    @Override
    public ListOfUserProfileDataResource toResource(ListOfUserProfileData listOfUserProfileData) {

        ListOfUserProfileDataResource res = new ListOfUserProfileDataResource();
        res.setUserProfileDataList(listOfUserProfileData.getUserProfileDataList());

        Link link = linkTo(SearchController.class).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
