package com.walker.rest.mvc;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.Id;
import com.walker.core.entities.UserProfileData;
import com.walker.core.services.FriendService;
import com.walker.core.services.impl.FriendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Rafal on 16.06.2017.
 */
@Controller
@RequestMapping("/friends")
public class FriendController {

    private FriendService service;

    @Autowired
    private Id id;

    public FriendController()
    {
        service = new FriendServiceImpl();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserProfileData>> getUserFriends()
    {
        List<UserProfileData> listOfFriends;
        try {
            listOfFriends = service.getFriends(id.getId());
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listOfFriends, HttpStatus.OK);
    }

    @RequestMapping(value = "/accept/{friendId}",  method = RequestMethod.POST)
    public ResponseEntity setFriend(@PathVariable int friendId)
    {
        try {
            service.acceptInviteFriend(id.getId(), friendId);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{friendId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteFriend(@PathVariable int friendId)
    {
        try {
            service.deleteFriend(id.getId(), friendId);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/{friendId}", method = RequestMethod.POST)
    public ResponseEntity inviteFriend(@PathVariable int friendId)
    {
        try {
            service.inviteFriend(id.getId(), friendId);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


}
