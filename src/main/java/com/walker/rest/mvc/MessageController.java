package com.walker.rest.mvc;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.ChatData;
import com.walker.core.entities.UserMessageData;
import com.walker.core.services.MessageService;
import com.walker.core.services.impl.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    private MessageService service;

    public MessageController() {
        this.service = new MessageServiceImpl();
    }

    @RequestMapping("/{idUser}")
    public ResponseEntity<List<UserMessageData>> getUserMessages(@PathVariable int idUser) {
        List<UserMessageData> userMessageDataList;
        try {
            userMessageDataList = service.getUserMessages(getCurrentUserId(), idUser);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMessageDataList, HttpStatus.OK);
    }

    @RequestMapping("/")
    public ResponseEntity<List<UserMessageData>> getAllUserMessages() {
        List<UserMessageData> userMessageDataList;
        try {
            userMessageDataList = service.getAllUserMessages(getCurrentUserId());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMessageDataList, HttpStatus.OK);
    }

    @RequestMapping("/add")
    public ResponseEntity getUserMessages(@RequestBody UserMessageData userMessageData) {
        try {
            service.addUserMessage(userMessageData);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/recentChat")
    public ResponseEntity<List<ChatData>> getRecentChatData() {
        List<ChatData> chatDataList;
        try {
            chatDataList = service.getRecentChatList(getCurrentUserId());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chatDataList, HttpStatus.OK);
    }

    private int getCurrentUserId()
    {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserIdFromNick(currentUser);
    }
}