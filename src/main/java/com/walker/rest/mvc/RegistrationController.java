package com.walker.rest.mvc;

import com.walker.core.entities.RegistrationForm;
import com.walker.core.services.UserService;
import com.walker.core.services.exception.UserExsistException;
import com.walker.core.services.impl.UserServiceImpl;
import com.walker.rest.exceptions.ConflictException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RegistrationForm> registration(@RequestBody RegistrationForm form)
    {
        try{
            RegistrationForm createdUser = service.addUserRegistration(form);
            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<RegistrationForm>(createdUser, headers, HttpStatus.CREATED);

        }catch (UserExsistException e)
        {
            return new ResponseEntity<RegistrationForm>(HttpStatus.CONFLICT);
        }
    }
}
