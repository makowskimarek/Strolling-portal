package com.walker.core.services;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.NotificationData;

import java.util.List;

/**
 * Created by Rafal on 10.06.2017.
 */
public interface NotificationService extends SessionService{
    public List<NotificationData> getUserNotification(int userId);
    public void ChangeStatus(int noitficationId) throws NotFoundException;
    public NotificationData getNotificationById(int id) throws NotFoundException;
}
