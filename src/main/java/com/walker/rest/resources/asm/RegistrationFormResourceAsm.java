package com.walker.rest.resources.asm;

import com.walker.core.entities.RegistrationForm;
import com.walker.rest.mvc.RegistrationController;
import com.walker.rest.resources.RegistrationFormResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 03.06.2017.
 */
public class RegistrationFormResourceAsm extends ResourceAssemblerSupport<RegistrationForm,RegistrationFormResource>{

    public RegistrationFormResourceAsm()
    {
        super(RegistrationController.class, RegistrationFormResource.class);
    }

    @Override
    public RegistrationFormResource toResource(RegistrationForm registrationForm) {
        RegistrationFormResource res = new RegistrationFormResource();
        res.setNick(registrationForm.getNick());
        res.setMail(registrationForm.getMail());
        res.setPassword("-");
        res.setFirstName(registrationForm.getFirstName());
        res.setLastName(registrationForm.getLastName());
        res.setCity(registrationForm.getCity());
        res.setDate(registrationForm.getDate());
        Link link = linkTo(RegistrationController.class).withSelfRel();
        res.add(link.withSelfRel());

        return res;
    }
}
