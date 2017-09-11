package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlLocation;
import com.walker.DataBaseControl.ControlPhoto;
import com.walker.DataBaseControl.ControlProfile;
import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.LocationData;
import com.walker.core.entities.PhotoData;
import com.walker.core.entities.ProfileData;
import com.walker.core.services.ProfileService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

/**
 * Created by Rafal on 31.07.2017.
 */
public class ProfileServiceImpl implements ProfileService{

    private ControlPhoto controlPhoto;
    private ControlProfile controlProfile;
    private ControlLocation controlLocation;
    private ControlUser controlUser;

    public ProfileServiceImpl()
    {
        controlPhoto = new ControlPhoto();
        controlLocation = new ControlLocation();
        controlProfile = new ControlProfile();
        controlUser = new ControlUser();
    }

    @Override
    public void updateLocation(int userId, LocationData locationData) {
        ProfileData profileData = controlProfile.getProfileData(userId);

        if(profileData == null)
        {
            int locationId = controlLocation.setLocationData(locationData);
            profileData = new ProfileData();
            profileData.setLocationId(locationId);
            controlProfile.addProfileData(userId, profileData);
        }else if(profileData.getLocationId() <= 0 ){
            int locationId = controlLocation.setLocationData(locationData);
            controlProfile.updateLocationid(userId,locationId);

        }else{
            controlLocation.updateLocation(profileData.getLocationId(),locationData);
        }
    }

    @Override
    public void updatePhoto(int userId, PhotoData photoData) {
        ProfileData profileData = controlProfile.getProfileData(userId);

        if(profileData == null)
        {
            int photoId = controlPhoto.addPhotoData(photoData);
            profileData = new ProfileData();
            profileData.setLocationId(photoId);
            controlProfile.addProfileData(userId, profileData);
        }else if(profileData.getPhotoId() <= 0 ){
            int photoId = controlPhoto.addPhotoData(photoData);
            controlProfile.updatePhotoid(userId,photoId);

        }else{
            controlPhoto.updatePhotoData(profileData.getPhotoId(),photoData);
        }
    }

    @Override
    public void updateDescription(int userId, String description) {
        ProfileData profileData = controlProfile.getProfileData(userId);
        if(profileData == null)
        {
            profileData = new ProfileData();
            profileData.setDescription(description);
            controlProfile.addProfileData(userId, profileData);
        }else
            controlProfile.updateDescription(userId, description);
    }

    @Override
    public LocationData getLocation(int userId) throws NotFoundException {
        ProfileData profileData = controlProfile.getProfileData(userId);

        if(profileData == null || profileData.getLocationId() <=0) throw new NotFoundException();

        return controlLocation.getLocationDataById(profileData.getLocationId());
    }

    @Override
    public String getDescription(int userId) throws NotFoundException{
        ProfileData profileData = controlProfile.getProfileData(userId);

        if(profileData == null || profileData.getDescription() == null) throw new NotFoundException();

        return profileData.getDescription();
    }

    @Override
    public PhotoData getPhoto(int userId) throws NotFoundException{
        PhotoData photoData = null;
        ProfileData profileData = controlProfile.getProfileData(userId);

        if(profileData != null )
            photoData = controlPhoto.getPhotoData(profileData.getPhotoId());

        if(photoData == null)
            photoData = new PhotoData();

        if(photoData.getData() == null || photoData.getData().equals("")) {
            ClassLoader classLoader = getClass().getClassLoader();

            URL url = classLoader.getResource("images/default_user_image.png");


            File file = new File(url.getFile());

            try {
                byte[] photo = Files.readAllBytes(file.toPath());
                photoData.setData(photo);
            } catch (IOException e) {
                throw new NotFoundException();
            }
        }
        return photoData;
    }

    @Override
    public int getUserIdFromNick(String nick) {
        return controlUser.getUserID(nick);
    }
}
