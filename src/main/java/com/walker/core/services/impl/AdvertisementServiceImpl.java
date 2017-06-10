package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlAdvertisement;
import com.walker.DataBaseControl.ControlNotification;
import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.AdvertisementData;
import com.walker.core.entities.NotificationData;
import com.walker.core.entities.UserData;
import com.walker.core.services.AdvertisementService;

import java.util.List;

/**
 * Created by Rafal on 10.06.2017.
 */
public class AdvertisementServiceImpl implements AdvertisementService {

    private ControlAdvertisement controlAdvertisement;
    private ControlNotification controlNotification;
    private ControlUser controlUser;

    public AdvertisementServiceImpl()
    {
        controlAdvertisement = new ControlAdvertisement();
        controlNotification = new ControlNotification();
        controlUser = new ControlUser();
    }

    @Override
    public void createAdvertisement(AdvertisementData advertisementData) {
        int advertisementId = controlAdvertisement.setAdvertisementData(advertisementData);

        if(advertisementData.getPrivacy().equals("friends"))
        {
            //dodaj powiadomienie
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
                "stroll",
                advertisementId,
                userData);
        controlNotification.setNotificationData(notificationData);
    }

    @Override
    public List<AdvertisementData> getUserAdvertisement(int userId) {
        return controlAdvertisement.getUserAdvertisement(userId);
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
}
