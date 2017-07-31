package com.walker.rest.mvc;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.Id;
import com.walker.core.entities.NotificationData;
import com.walker.core.services.NotificationService;
import com.walker.core.services.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Rafal on 10.06.2017.
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService service;

    public NotificationController()
    {
        service = new NotificationServiceImpl();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NotificationData>> getUserNotification()
    {
        List<NotificationData> list = service.getUserNotification(getCurrentUserId());

        if(list.size() == 0)
            return new ResponseEntity<List<NotificationData>>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<NotificationData>>(list, HttpStatus.OK);
    }

    @RequestMapping(value =  "/{idNotification}", method = RequestMethod.PUT)
    public ResponseEntity changeStatus(@PathVariable int idNotification)
    {
        NotificationData data;
        try {
            data =  service.getNotificationById(idNotification);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if(data.getUserId() != getCurrentUserId())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        try {
            service.ChangeStatus(idNotification);
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
