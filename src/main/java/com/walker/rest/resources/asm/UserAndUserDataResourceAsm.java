package com.walker.rest.resources.asm;

import com.walker.core.entities.UserAndUserData;
import com.walker.rest.mvc.RegistrationController;
import com.walker.rest.mvc.UserController;
import com.walker.rest.resources.RegistrationFormResource;
import com.walker.rest.resources.UserAndUserDataResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 04.06.2017.
 */
public class UserAndUserDataResourceAsm extends ResourceAssemblerSupport<UserAndUserData,UserAndUserDataResource> {

    public UserAndUserDataResourceAsm()
    {
        super(UserController.class, UserAndUserDataResource.class);
    }

    @Override
    public UserAndUserDataResource toResource(UserAndUserData userAndUserData) {

        UserAndUserDataResource res = new UserAndUserDataResource();
        res.setUser_id(userAndUserData.getUser_id());
        res.setNick(userAndUserData.getNick());
        res.setPassword("-");
        res.setMail(userAndUserData.getMail());
        res.setFirstName(userAndUserData.getFirstName());
        res.setLastName(userAndUserData.getLastName());
        res.setCity(userAndUserData.getCity());
        res.setDate(userAndUserData.getDate());

        Link link = linkTo(UserController.class).slash(res.getUser_id()).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
