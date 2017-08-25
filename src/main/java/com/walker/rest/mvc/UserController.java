package com.walker.rest.mvc;

import com.walker.core.entities.*;
import com.walker.core.services.UserService;
import com.walker.core.services.exception.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<UserProfileData> updateData(@RequestBody UserData userData)
    {
        int userId = getCurrentUserId();
        service.updateUserData(userId, userData);
        UserProfileData userProfileData = service.getUserProfileData(userId);
        return new ResponseEntity<UserProfileData>(userProfileData, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateMail", method = RequestMethod.POST)
    public ResponseEntity<UserProfileData> updateMail(@RequestBody UserMail userMail)
    {
        int userId = getCurrentUserId();
        service.updateUserMail(userId, userMail);
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

    private int getCurrentUserId()
    {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserIdFromNick(currentUser);
    }

}
