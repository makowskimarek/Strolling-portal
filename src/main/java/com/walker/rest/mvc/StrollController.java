package com.walker.rest.mvc;

import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.StrollData;
import com.walker.core.services.exception.PasswordException;
import com.walker.core.services.impl.StrollServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */

@Controller
@RequestMapping("/stroll")
public class StrollController {


    private StrollServiceImpl service;



    public StrollController() {
        service = new StrollServiceImpl();
    }


    @RequestMapping(value = "/makeStroll", method = RequestMethod.POST)
    public ResponseEntity makeStroll(@RequestBody StrollData strollData) {
        try {
            service.addStroll(strollData, getCurrentUserId());
            return new ResponseEntity(HttpStatus.OK);
        } catch (PasswordException | NoUserException | WrongLocationException | NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateStroll(@RequestBody StrollData strollData) {
        try {
            service.editStroll(strollData);
            return new ResponseEntity(HttpStatus.OK);
        } catch (PasswordException | NoUserException | WrongLocationException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @RequestMapping(value = "/delete/{strollId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStroll(@PathVariable int strollId) {
        try {
            service.deleteStroll(strollId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (PasswordException | NoUserException | WrongLocationException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @RequestMapping(value = "/get/{strollId}", method = RequestMethod.GET)
    public ResponseEntity getStrollByStrollId(@PathVariable int strollId) {
        try {
            StrollData strollData = null;
            strollData = service.getStrollById(strollId, getCurrentUserId());
            return new ResponseEntity(strollData, HttpStatus.OK);
        } catch (PasswordException | NoUserException | WrongLocationException | NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getStroll() {
        List<StrollData> list = null;
        try {
            list = service.getStrollByUserId(getCurrentUserId());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (PasswordException | NoUserException | WrongLocationException | NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    private int getCurrentUserId()
    {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserIdFromNick(currentUser);
    }
}