package com.walker.rest.mvc;

import com.walker.core.entities.RegistrationForm;
import com.walker.core.services.UserService;
import com.walker.core.services.exception.UserExsistException;
import com.walker.core.services.impl.UserServiceImpl;
import com.walker.rest.exceptions.ConflictException;
import com.walker.rest.resources.RegistrationFormResource;
import com.walker.rest.resources.asm.RegistrationFormResourceAsm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

/**
 * Created by Rafal on 03.06.2017.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService service;

    public RegistrationController()
    {
        this.service = new UserServiceImpl();
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RegistrationFormResource> registration(@RequestBody RegistrationFormResource form)
    {
        try{
            RegistrationForm createdUser = service.addUserRegistration(form.toRegistrationForm());
            RegistrationFormResource res = new RegistrationFormResourceAsm().toResource(createdUser);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<RegistrationFormResource>(res, headers, HttpStatus.CREATED);

        }catch (UserExsistException e)
        {
            throw new ConflictException(e);
        }
    }
}
