package com.walker.rest.mvc;

import com.walker.core.entities.PasswordForm;
import com.walker.core.entities.UserAndUserData;
import com.walker.core.entities.UserMail;
import com.walker.core.entities.UserProfileData;
import com.walker.core.services.UserService;
import com.walker.core.services.exception.PasswordException;
import com.walker.core.services.impl.UserServiceImpl;
import com.walker.rest.resources.UserAndUserDataResource;
import com.walker.rest.resources.UserDataResource;
import com.walker.rest.resources.UserMailResource;
import com.walker.rest.resources.UserProfileDataResources;
import com.walker.rest.resources.asm.UserAndUserDataResourceAsm;
import com.walker.rest.resources.asm.UserProfileDataResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rafal on 04.06.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService service;

    public UserController()
    {
        this.service = new UserServiceImpl();
    }

    @RequestMapping("/{idUser}")
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
    public ResponseEntity<UserProfileDataResources> getUserAndUserData()
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

    }

    private int getCurrentUserId()
    {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserIdFromNick(currentUser);
    }

}
