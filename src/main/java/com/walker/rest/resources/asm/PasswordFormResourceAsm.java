package com.walker.rest.resources.asm;

import com.walker.core.entities.PasswordForm;
import com.walker.rest.mvc.UserController;
import com.walker.rest.resources.PasswordFormResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 04.06.2017.
 */
public class PasswordFormResourceAsm extends ResourceAssemblerSupport<PasswordForm, PasswordFormResource> {

    public PasswordFormResourceAsm()
    {
        super(UserController.class, PasswordFormResource.class);
    }

    @Override
    public PasswordFormResource toResource(PasswordForm passwordForm) {
        PasswordFormResource res = new PasswordFormResource();
        res.setOldPassword(passwordForm.getOldPassword());
        res.setNewPassword(passwordForm.getNewPassword());
        res.setConfirmPassword(passwordForm.getConfirmPassword());

        Link link = linkTo(UserController.class).withSelfRel();
        res.add(link.withSelfRel());
        return res;
        }
}
