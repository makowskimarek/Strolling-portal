package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlNotification;
import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.NotificationData;
import com.walker.core.services.NotificationService;

import java.util.List;

/**
 * Created by Rafal on 10.06.2017.
 */
public class NotificationServiceImpl implements NotificationService {

    private ControlNotification controlNotification;
    private ControlUser controlUser;

    public NotificationServiceImpl()
    {
        controlNotification = new ControlNotification();
        controlUser = new ControlUser();
    }

    @Override
    public List<NotificationData> getUserNotification(int userId) {
        return controlNotification.getUserNotification(userId);
    }

    @Override
    public void ChangeStatus(int noitficationId) throws NotFoundException {
        NotificationData notificationData = controlNotification.getNotificationDataById(noitficationId);

        notificationData.setStatus("checked");

        controlNotification.updateNotification(notificationData);
    }

    @Override
    public NotificationData getNotificationById(int id) throws NotFoundException {
        return controlNotification.getNotificationDataById(id);
    }

    @Override
    public int getUserIdFromNick(String nick) {
        return controlUser.getUserID(nick);
    }
}
