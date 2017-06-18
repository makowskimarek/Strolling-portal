package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlAdvertisement;
import com.walker.DataBaseControl.ControlFriend;
import com.walker.DataBaseControl.ControlNotification;
import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.*;
import com.walker.core.services.AdvertisementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafal on 10.06.2017.
 */
public class AdvertisementServiceImpl implements AdvertisementService {

    private ControlAdvertisement controlAdvertisement;
    private ControlNotification controlNotification;
    private ControlUser controlUser;
    private ControlFriend controlFriend;

    public AdvertisementServiceImpl()
    {
        controlAdvertisement = new ControlAdvertisement();
        controlNotification = new ControlNotification();
        controlUser = new ControlUser();
        controlFriend = new ControlFriend();
    }

    @Override
    public void createAdvertisement(int senderId, AdvertisementData advertisementData) {
        int advertisementId = controlAdvertisement.setAdvertisementData(advertisementData);

        if(advertisementData.getPrivacy().equals("Friends"))
        {
            UserData userData = controlUser.getUserData(senderId);
            List<Friend> listOfFriendsId = null;

            try {
                listOfFriendsId = controlFriend.getUserFriends(senderId);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }

            int friendId;
            for(Friend friends : listOfFriendsId) {

                if (friends.getUser1Id() == senderId)
                    friendId = friends.getUser2Id();
                else
                    friendId = friends.getUser1Id();

                NotificationData notificationData = new NotificationData(
                        0,
                        friendId,
                        "notChecked",
                        "Stroll",
                        advertisementId,
                        userData);
                controlNotification.setNotificationData(notificationData);
            }

        }
    }

    @Override
    public void invitePersonToStroll(int senderId, int InvitedId, AdvertisementData advertisementData) {
        advertisementData.setPrivacy("hide");
        int advertisementId = controlAdvertisement.setAdvertisementData(advertisementData);

        UserData userData = controlUser.getUserData(senderId);

        NotificationData notificationData = new NotificationData(
                0,
                InvitedId,
                "notChecked",
                "Stroll",
                advertisementId,
                userData);
        controlNotification.setNotificationData(notificationData);
    }

    @Override
    public List<AdvertisementData> getUserAdvertisement(int userId) throws NotFoundException{

        List<AdvertisementData> list = controlAdvertisement.getUserAdvertisement(userId);

        if(list.size() == 0) throw new NotFoundException();

        return list;
    }

    @Override
    public void deleteAdvertisement(int advertisementId) {
        controlAdvertisement.deleteAdvertisementData(advertisementId);
    }

    @Override
    public void updateAdvertisement(AdvertisementData advertisementData) throws NotFoundException {
        controlAdvertisement.updateAdvertisementData(advertisementData);
    }

    @Override
    public AdvertisementData getAdvertisementById(int id) throws NotFoundException {
        return controlAdvertisement.getAdvertisementData(id);
    }

    @Override
    public List<AdvertisementData> getAllAdvertisement() throws NotFoundException {

        List<AdvertisementData> list = controlAdvertisement.getAdvertisementwithPrivacy("All");

        if(list.size()==0) throw new NotFoundException();

        return list;
    }

    @Override
    public List<AdvertisementData> getFriendsAdvertisement(int userId) throws NotFoundException {

        List<AdvertisementData> listOfFriendsAdvertisement = controlAdvertisement.getAdvertisementwithPrivacy("Friends");

        List<Friend> listOfFriendsId = controlFriend.getUserFriends(userId);


        int friendId;
        boolean advertisementToyou;
        for(int i = 0; i < listOfFriendsAdvertisement.size();i++) {
            advertisementToyou = false;
            for (Friend friends : listOfFriendsId) {

                if (friends.getUser1Id() == userId)
                    friendId = friends.getUser2Id();
                else
                    friendId = friends.getUser1Id();

                if(friendId == listOfFriendsAdvertisement.get(i).getUserId())
                    advertisementToyou = false;


            }
            if(advertisementToyou) listOfFriendsAdvertisement.remove(i);
        }

        if(listOfFriendsAdvertisement.size() == 0) throw new NotFoundException();

        return listOfFriendsAdvertisement;
    }
}
