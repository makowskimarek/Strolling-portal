package com.walker.core.services;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.*;

import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public interface MessageService extends SessionService{

    void addUserMessage(UserMessageData userMessageData);
    List<UserMessageData> getAllUserMessages(int userId) throws NotFoundException;
    List<UserMessageData> getUserMessages(int userId, int userId2) throws NotFoundException;
    List<ChatData> getRecentChatList(int uderId) throws NotFoundException;

}