package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlFriend;
import com.walker.DataBaseControl.ControlNotification;
import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.*;
import com.walker.core.services.FriendService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafal on 16.06.2017.
 */
public class FriendServiceImpl implements FriendService{

    private ControlFriend controlFriend;
    private ControlUser controlUser;
    private ControlNotification controlNotification;

    public FriendServiceImpl()
    {
        controlFriend = new ControlFriend();
        controlUser = new ControlUser();
        controlNotification = new ControlNotification();
    }

    @Override
    public void inviteFriend(int userId, int invitedId) throws NotFoundException{

        UserProfileData sender = controlUser.getUserProfileData(userId);
        UserData invited = controlUser.getUserData(invitedId);

        if(sender == null || invited == null) throw new NotFoundException();

        NotificationData notification = new NotificationData(
                0,
                invitedId,
                "notChecked",
                "Friend",
                0,
                sender);

        controlNotification.setNotificationData(notification);
    }

    @Override
    public void acceptInviteFriend(int invitedId, int invitingId) throws NotFoundException{

        if(!controlUser.checktUserExsist(invitedId)) throw new NotFoundException();
        if(!controlUser.checktUserExsist(invitingId)) throw new NotFoundException();

        List<NotificationData> list = controlNotification.getUserNotification(invitedId);

        boolean ifInvited = false;
        for(NotificationData notification : list)
        {
            if(notification.getSender().getUser_id() == invitingId && !notification.getStatus().equals("checked")) {
                notification.setStatus("checked");
                controlNotification.updateNotification(notification);
                ifInvited = true;
            }
        }

        if(ifInvited)
            controlFriend.addFriend(new Friend(invitedId, invitingId));
        else
            throw new NotFoundException();
    }

    @Override
    public void deleteFriend(int userId, int friendId) throws NotFoundException {

        if(!controlUser.checktUserExsist(userId)) throw new NotFoundException();
        if(!controlUser.checktUserExsist(friendId)) throw new NotFoundException();

        boolean deleted = false;
        List<Friend> list = controlFriend.getUserFriends(userId);
        for(Friend friend:list)
        {
            if(friend.getUser1Id() == friendId) {
                controlFriend.deleteFriends(new Friend(friendId, userId));
                deleted = true;
                break;
            }

            if(friend.getUser2Id() == friendId){
                controlFriend.deleteFriends(new Friend(userId,friendId));
                deleted = true;
                break;
            }
        }

        if(!deleted) throw new NotFoundException();
    }

    @Override
    public List<UserProfileData> getFriends(int userId) throws NotFoundException{

        User user = controlUser.getUser(userId);
        if(user == null) throw new NotFoundException();

        List<Friend> listOfFriendsId = controlFriend.getUserFriends(userId);

        List<UserProfileData> userProfileDataList = new ArrayList<UserProfileData>();
        UserProfileData friendData;
        int friendId;
        for(Friend friends : listOfFriendsId) {

            if (friends.getUser1Id() == userId)
                friendId = friends.getUser2Id();
            else
                friendId = friends.getUser1Id();

            friendData = controlUser.getUserProfileData(friendId);

            if (friendData != null)
                userProfileDataList.add(friendData);
        }

        if(userProfileDataList.size() == 0) throw new NotFoundException();

        return userProfileDataList;
    }
}
