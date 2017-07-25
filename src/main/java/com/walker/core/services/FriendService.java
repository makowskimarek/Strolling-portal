package com.walker.core.services;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.UserProfileData;

import java.util.List;

/**
 * Created by Rafal on 16.06.2017.
 */
public interface FriendService extends SessionService{
    public void inviteFriend(int userId, int invitedId) throws NotFoundException;
    public void acceptInviteFriend(int invitedId, int invitingId) throws NotFoundException;
    public void deleteFriend(int userId, int friendId) throws NotFoundException;
    public List<UserProfileData> getFriends(int userId) throws NotFoundException;
}
