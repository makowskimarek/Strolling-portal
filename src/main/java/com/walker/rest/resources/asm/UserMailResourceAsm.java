package com.walker.rest.resources.asm;

import com.walker.core.entities.UserMail;
import com.walker.rest.mvc.UserController;
import com.walker.rest.resources.UserAndUserDataResource;
import com.walker.rest.resources.UserMailResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 04.06.2017.
 */
public class UserMailResourceAsm extends ResourceAssemblerSupport<UserMail, UserMailResource> {

    public UserMailResourceAsm()
    {
        super(UserController.class, UserMailResource.class);
    }

    @Override
    public UserMailResource toResource(UserMail userMail) {

        UserMailResource res = new UserMailResource();
        res.setMail(userMail.getMail());

        Link link = linkTo(UserController.class).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
