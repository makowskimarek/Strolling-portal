package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlLocation;
import com.walker.DataBaseControl.ControlPhoto;
import com.walker.DataBaseControl.ControlProfile;
import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.*;
import com.walker.core.services.UserService;
import com.walker.core.services.exception.PasswordException;
import com.walker.core.services.exception.UserExsistException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rafal on 03.06.2017.
 */
public class UserServiceImpl implements UserService {

    private ControlUser controlUser;
    private ControlPhoto controlPhoto;
    private ControlProfile controlProfile;
    private ControlLocation controlLocation;

    public UserServiceImpl()
    {
        controlPhoto = new ControlPhoto();
        controlLocation = new ControlLocation();
        controlProfile = new ControlProfile();
        controlUser = new ControlUser();
    }

    @Override
    public int getUserIdFromNick(String nick) {
        return controlUser.getUserID(nick);
    }

    @Override
    public RegistrationForm addUserRegistration(RegistrationForm form) throws UserExsistException{
        int IdUser;
        User user;

        user = controlUser.getUser(form.getNick());
        if(user == null) {

            user = new User(0,
                    form.getNick(),
                    form.getPassword(),
                    form.getMail()
                    );
            controlUser.addUser(user);

            IdUser = controlUser.getUserID(form.getNick());
            user.setUser_id(IdUser);

            controlUser.addUserData(
                    new UserData(IdUser,
                            form.getFirstName(),
                            form.getLastName(),
                            form.getCity(),
                            form.getDate()));

            createProfileData(IdUser);
        }
        else
            throw new UserExsistException();

        return form;
    }

    private void createProfileData(int userId)
    {
        LocationData locationData = new LocationData();
        PhotoData photoData = new PhotoData();
        photoData.setData(getDefaultPhoto());
        photoData.setTook_time(getActualyDateAsString());

        int idLocation = controlLocation.setLocationData(locationData);
        int idPhoto = controlPhoto.addPhotoData(photoData);

        ProfileData profileData = new ProfileData();
        profileData.setDescription("not set");
        profileData.setLocationId(idLocation);
        profileData.setPhotoId(idPhoto);

        controlProfile.addProfileData(userId, profileData);
    }

    private String getActualyDateAsString(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    private byte[] getDefaultPhoto()
    {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("images/default_user_image.png");
        File file = new File(url.getFile());
        byte[] photo = null;
        try {
            photo = Files.readAllBytes(file.toPath());

        } catch (IOException e) {

        }
        return photo;
    }

    @Override
    public void updateUserData(int userId, UserData userData) {
        controlUser.updateUserData(userId,userData);
    }

    @Override
    public void updateUserMail(int userId, UserMail userMail) {
        controlUser.updateUserMail(userId, userMail.getMail());
    }

    @Override
    public void updatePassword(int userId, PasswordForm form) throws PasswordException{

        User user = controlUser.getUser(userId);
        if(user.getPassword().equals(form.getOldPassword())&&form.getNewPassword().equals(form.getConfirmPassword()))
        {
                user.setPassword(form.getNewPassword());
                controlUser.updateUser(user.getUser_id(), user);
        }
        else
            throw new PasswordException();
    }

    @Override
    public UserAndUserData getUserAndUserData(int userId) {
        User user = controlUser.getUser(userId);
        UserData userData = controlUser.getUserData(userId);

        if(user == null || userData == null)
            return null;
        else
            return new UserAndUserData(user, userData);
    }
    @Override
    public UserAndUserData getUserAndUserData(String nick) {
        User user = controlUser.getUser(nick);
        if(user != null) {
            UserData userData = controlUser.getUserData(user.getUser_id());

            if (userData == null)
                return null;
            else
                return new UserAndUserData(user, userData);
        }
        else
            return null;
    }
    @Override
    public UserProfileData getUserProfileData(int userId)
    {
        UserProfileData userProfileData = controlUser.getUserProfileData(userId);
        if(userProfileData.getPhoto_url() == null || userProfileData.getPhoto_url().equals("")) {
            ClassLoader classLoader = getClass().getClassLoader();
            URL url = classLoader.getResource("images/default_user_image.png");
            File file = new File(url.getFile());

            try {
                byte[] photo = Files.readAllBytes(file.toPath());
                userProfileData.setPhoto_url(photo);
            } catch (IOException e) {

            }
        }
        return userProfileData;
    }

    @Override
    public PhotoData getPhotoData(int userId) {
        //controlUser.g
        return null;
    }

    @Override
    public void changePhoto(int userId, PhotoData photoData) {

    }
}
