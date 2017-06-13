package com.walker.rest.mvc;

import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.Id;
import com.walker.core.entities.StrollData;
import com.walker.core.services.exception.PasswordException;
import com.walker.core.services.impl.StrollServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Marek Makowski
 * @version 1.0
 */

@Controller
@RequestMapping("/stroll")
public class StrollController {


    private StrollServiceImpl service;

    @Autowired
    private Id id;

    public StrollController()
    {
        service = new StrollServiceImpl();
    }


    @RequestMapping(value = "/makeStroll", method = RequestMethod.POST)
    public ResponseEntity makeStroll(@RequestBody StrollData strollData)
    {
        //Jak daje LoginData loginData zamiast InvitationData invitationData
        // to postman bez problemów się komunikuje jak jest tak jak teraz to dupa
       // int userId = getCurrentUserId();

        try{
            service.addStroll(strollData);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (PasswordException | NoUserException | WrongLocationException | NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}