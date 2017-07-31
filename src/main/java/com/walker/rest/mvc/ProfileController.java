package com.walker.rest.mvc;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.LocationData;
import com.walker.core.entities.PhotoData;
import com.walker.core.services.ProfileService;
import com.walker.core.services.impl.ProfileServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rafal on 31.07.2017.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    ProfileService service = new ProfileServiceImpl();


    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public ResponseEntity<LocationData> getLocation(){
        LocationData data;
        try {
            data = service.getLocation(getCurrentUserId());
        } catch (NotFoundException e) {
            return new ResponseEntity<LocationData>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LocationData>(data, HttpStatus.OK );
    }

    @RequestMapping(value = "/photo", method = RequestMethod.GET)
    public ResponseEntity<PhotoData> getPhoto(){
        PhotoData data;
        try {
            data = service.getPhoto(getCurrentUserId());
        } catch (NotFoundException e) {
            return new ResponseEntity<PhotoData>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PhotoData>(data, HttpStatus.OK );
    }

    @RequestMapping(value = "/description", method = RequestMethod.GET)
    public ResponseEntity<String> getDescription(){
        String data;
        try {
            data = service.getDescription(getCurrentUserId());
        } catch (NotFoundException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(data, HttpStatus.OK );
    }

    @RequestMapping(value = "/location", method = RequestMethod.PUT)
    public ResponseEntity updateLocation(@RequestBody LocationData locationData) {

        service.updateLocation(getCurrentUserId(), locationData);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/description", method = RequestMethod.PUT)
    public ResponseEntity updateDescription(@RequestBody String description) {

        service.updateDescription(getCurrentUserId(), description);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/photo", method = RequestMethod.PUT)
    public ResponseEntity updatePhoto(@RequestBody PhotoData photoData) {

        service.updatePhoto(getCurrentUserId(), photoData);
        return new ResponseEntity(HttpStatus.OK);
    }


    private int getCurrentUserId()
    {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserIdFromNick(currentUser);
    }
}
