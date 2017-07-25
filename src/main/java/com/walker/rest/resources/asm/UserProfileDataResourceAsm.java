package com.walker.rest.resources.asm;

import com.walker.core.entities.UserProfileData;
import com.walker.rest.mvc.UserController;
import com.walker.rest.resources.UserProfileDataResources;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 04.06.2017.
 */
public class UserProfileDataResourceAsm extends ResourceAssemblerSupport<UserProfileData,UserProfileDataResources> {

    public UserProfileDataResourceAsm()
    {
        super(UserController.class,UserProfileDataResources.class);
    }

    @Override
    public UserProfileDataResources toResource(UserProfileData userProfileData) {
        UserProfileDataResources res = new UserProfileDataResources(
                userProfileData.getUser_id(),
                userProfileData.getNick(),
                userProfileData.getFirstName(),
                userProfileData.getLastName(),
                userProfileData.getCity(),
                userProfileData.getBirth_date(),
                userProfileData.getLatitude(),
                userProfileData.getLongtitude(),
                userProfileData.getDescription(),
                userProfileData.getPhoto_url(),
                userProfileData.getEmail()
        );

        Link link = linkTo(UserController.class).slash(userProfileData.getUser_id()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
