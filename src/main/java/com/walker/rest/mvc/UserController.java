package com.walker.rest.mvc;

import com.walker.core.entities.Id;
import com.walker.core.entities.PasswordForm;
import com.walker.core.entities.UserProfileData;
import com.walker.core.services.UserService;
import com.walker.core.services.exception.PasswordException;
import com.walker.rest.resources.UserDataResource;
import com.walker.rest.resources.UserMailResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Rafal on 04.06.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private Id id;


    @RequestMapping("/{idUser}")
    public ResponseEntity<UserProfileData> getUserProfileData(@PathVariable int idUser)
    {
        UserProfileData userProfileData = service.getUserProfileData(idUser);

        if(userProfileData != null)
        {
            return new ResponseEntity<UserProfileData>(userProfileData, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserProfileData>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserProfileData> getUserAndUserData(HttpServletRequest request)
    {
        UserProfileData userProfileData = service.getUserProfileData(getCurrentUserId());

        if(userProfileData != null)
        {
            return new ResponseEntity<UserProfileData>(userProfileData, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserProfileData>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public ResponseEntity<UserProfileData> updateData(@RequestBody UserDataResource userDataResource)
    {
        int userId = getCurrentUserId();
        service.updateUserData(userId, userDataResource.toUserData());
        UserProfileData userProfileData = service.getUserProfileData(userId);
        return new ResponseEntity<UserProfileData>(userProfileData, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateMail", method = RequestMethod.POST)
    public ResponseEntity<UserProfileData> updateMail(@RequestBody UserMailResource userMailResource)
    {
        int userId = getCurrentUserId();
        service.updateUserMail(userId, userMailResource.toUserMail());
        UserProfileData userProfileData = service.getUserProfileData(userId);
        return new ResponseEntity<UserProfileData>(userProfileData, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResponseEntity<UserProfileData> updatePassword(@RequestBody PasswordForm passwordForm)
    {
        int userId = getCurrentUserId();

        try{
            service.updatePassword(userId,passwordForm);
            UserProfileData userProfileData = service.getUserProfileData(userId);
            return new ResponseEntity<UserProfileData>(userProfileData, HttpStatus.OK);
        }
        catch (PasswordException e) {
            return new ResponseEntity<UserProfileData>(HttpStatus.NOT_ACCEPTABLE);
        }

    }


    /*@RequestMapping("/{idUser}")
    public ResponseEntity<UserProfileDataResources> getUserProfileData(@PathVariable int idUser)
    {
        UserProfileData userProfileData = service.getUserProfileData(idUser);

        if(userProfileData != null)
        {
            UserProfileDataResources res = new UserProfileDataResourceAsm().toResource(userProfileData);
            return new ResponseEntity<UserProfileDataResources>(res, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserProfileDataResources>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserProfileDataResources> getUserAndUserData(HttpServletRequest request)
    {
        UserProfileData userProfileData = service.getUserProfileData(getCurrentUserId());

        if(userProfileData != null)
        {
            UserProfileDataResources res = new UserProfileDataResourceAsm().toResource(userProfileData);
            return new ResponseEntity<UserProfileDataResources>(res, HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserProfileDataResources>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    public ResponseEntity<UserProfileDataResources> updateData(@RequestBody UserDataResource userDataResource)
    {
        int userId = getCurrentUserId();
        service.updateUserData(userId, userDataResource.toUserData());
        UserProfileData userProfileData = service.getUserProfileData(userId);
        UserProfileDataResources res = new UserProfileDataResourceAsm().toResource(userProfileData);
        return new ResponseEntity<UserProfileDataResources>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateMail", method = RequestMethod.POST)
    public ResponseEntity<UserProfileDataResources> updateMail(@RequestBody UserMailResource userMailResource)
    {
        int userId = getCurrentUserId();
        service.updateUserMail(userId, userMailResource.toUserMail());
        UserProfileData userProfileData = service.getUserProfileData(userId);
        UserProfileDataResources res = new UserProfileDataResourceAsm().toResource(userProfileData);
        return new ResponseEntity<UserProfileDataResources>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResponseEntity<UserProfileDataResources> updatePassword(@RequestBody PasswordForm passwordForm)
    {
        int userId = getCurrentUserId();

        try{
            service.updatePassword(userId,passwordForm);
            UserProfileData userProfileData = service.getUserProfileData(userId);
            UserProfileDataResources res = new UserProfileDataResourceAsm().toResource(userProfileData);
            return new ResponseEntity<UserProfileDataResources>(res, HttpStatus.OK);
        }
        catch (PasswordException e) {
            return new ResponseEntity<UserProfileDataResources>(HttpStatus.NOT_ACCEPTABLE);
        }

    }*/



    private int getCurrentUserId()
    {
        return id.getId();
    }

}
