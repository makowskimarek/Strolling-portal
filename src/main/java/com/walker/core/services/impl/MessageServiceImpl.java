package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.ControlUserMessage;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.ChatData;
import com.walker.core.entities.UserMessageData;
import com.walker.core.services.MessageService;

import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class MessageServiceImpl implements MessageService {

    private ControlUserMessage controlUserMessage;
    private ControlUser controlUser;

    public MessageServiceImpl() {
        controlUserMessage = new ControlUserMessage();
        controlUser = new ControlUser();
    }

    @Override
    public List<UserMessageData> getAllUserMessages(int userId) throws NotFoundException {
        try {
            List<UserMessageData> userMessagesList = controlUserMessage.getAllUserMessages(userId);
            return userMessagesList;
        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public List<UserMessageData> getUserMessages(int userId, int userId2) throws NotFoundException {
        try {
            List<UserMessageData> userMessagesList = controlUserMessage.getUserMessages(userId, userId2);
            return userMessagesList;
        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public List<ChatData> getRecentChatList(int userId) throws NotFoundException {
        try {
            List<ChatData> chatDataList = controlUserMessage.getRecentChatList(userId);
            return chatDataList;
        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void addUserMessage(UserMessageData userMessageData) {
        controlUserMessage.addUserMessage(userMessageData);
    }

    @Override
    public int getUserIdFromNick(String nick) {
        return controlUser.getUserID(nick);
    }


}
