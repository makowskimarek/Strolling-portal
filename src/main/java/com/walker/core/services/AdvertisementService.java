package com.walker.core.services;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.AdvertisementData;

import java.util.List;

/**
 * Created by Rafal on 10.06.2017.
 */
public interface AdvertisementService {

    public void createAdvertisement(AdvertisementData advertisementData);
    public void invitePersonToStroll(int senderId, int userId, AdvertisementData advertisementData);
    public List<AdvertisementData> getUserAdvertisement(int userId);
    public void deleteAdvertisement(int advertisementId);
    public void updateAdvertisement(AdvertisementData advertisementData) throws NotFoundException;
    public AdvertisementData getAdvertisementById(int id) throws NotFoundException;
}
