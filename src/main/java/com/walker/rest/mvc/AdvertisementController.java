package com.walker.rest.mvc;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.AdvertisementData;
import com.walker.core.entities.Id;
import com.walker.core.services.AdvertisementService;
import com.walker.core.services.impl.AdvertisementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rafal on 10.06.2017.
 */
@Controller
@RequestMapping("/adv")
public class AdvertisementController {

    private AdvertisementService service;



    public AdvertisementController()
    {
        service = new AdvertisementServiceImpl();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAdvertisement(@RequestBody AdvertisementData  advertisementData)
    {
        int idCurrentUser = getCurrentUserId();
        advertisementData.setUserId(idCurrentUser);
        service.createAdvertisement(idCurrentUser,advertisementData);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{idUser}",method = RequestMethod.POST)
    public ResponseEntity invitePresonToStroll(@PathVariable int idUser, @RequestBody AdvertisementData advertisementData)
    {
        int idCurrentUser = getCurrentUserId();
        advertisementData.setUserId(idCurrentUser);
        service.invitePersonToStroll(idCurrentUser,idUser,advertisementData);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{idAdvertisement}", method = RequestMethod.GET)
    public ResponseEntity<AdvertisementData> getAdvertisementById(@PathVariable int idAdvertisement)
    {
        AdvertisementData data;
        try {
            data = service.getAdvertisementById(idAdvertisement);
        } catch (NotFoundException e) {
            return new ResponseEntity<AdvertisementData>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AdvertisementData>(data, HttpStatus.OK );
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementData>> getUserAdvertisement()
    {
        List<AdvertisementData> list = null;
        try {
            list = service.getUserAdvertisement(getCurrentUserId());
        } catch (NotFoundException e) {
            return new ResponseEntity<List<AdvertisementData>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<AdvertisementData>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementData>> getAllAdvertisement()
    {
        List<AdvertisementData> list = null;
        try {
            list = service.getAllAdvertisement();
        } catch (NotFoundException e) {
            return new ResponseEntity<List<AdvertisementData>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<AdvertisementData>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementData>> getFriendAdvertisement()
    {
        List<AdvertisementData> list = null;
        try {
            list = service.getFriendsAdvertisement(getCurrentUserId());
        } catch (NotFoundException e) {
            return new ResponseEntity<List<AdvertisementData>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<AdvertisementData>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{idAdvertisement}",method = RequestMethod.DELETE)
    public ResponseEntity deleteAdvertisement(@PathVariable int idAdvertisement)
    {
        try {
            service.deleteAdvertisement(getCurrentUserId(), idAdvertisement);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateAdvertisement(@RequestBody AdvertisementData advertisementData)
    {
        advertisementData.setUserId(getCurrentUserId());
        try {
            service.updateAdvertisement(advertisementData);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    private int getCurrentUserId()
    {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserIdFromNick(currentUser);
    }
}
