package com.walker.rest.resources.asm;

import com.walker.core.entities.UserData;
import com.walker.rest.mvc.UserController;
import com.walker.rest.resources.UserDataResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 04.06.2017.
 */
public class UserDataResourceAsm extends ResourceAssemblerSupport<UserData,UserDataResource> {

    public UserDataResourceAsm()
    {
        super(UserController.class,UserDataResource.class);
    }

    @Override
    public UserDataResource toResource(UserData userData) {

        UserDataResource res = new UserDataResource(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getCity(),
                userData.getDate()
        ) ;

        Link link = linkTo(UserController.class).slash(userData.getUserId()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
