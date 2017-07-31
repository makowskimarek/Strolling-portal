package com.walker.core.services;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.LocationData;
import com.walker.core.entities.PhotoData;

/**
 * Created by Rafal on 31.07.2017.
 */
public interface ProfileService extends SessionService{
    public void updateLocation(int userId, LocationData locationData);
    public void updatePhoto(int userId, PhotoData photoData);
    public void updateDescription(int userId, String description);
    public LocationData getLocation(int userId) throws NotFoundException;
    public String getDescription(int userId) throws NotFoundException;
    public PhotoData getPhoto(int userId) throws NotFoundException;
}
