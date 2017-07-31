package com.walker.core.services;

import com.walker.core.entities.*;

/**
 * Created by Rafal on 03.06.2017.
 */
public interface UserService extends SessionService{

    RegistrationForm addUserRegistration(RegistrationForm form);
    void updateUserData(int userId,UserData userData);
    void updateUserMail(int userId, UserMail userMail);
    void updatePassword(int userId, PasswordForm passwordForm);
    UserAndUserData getUserAndUserData(int userId);
    UserAndUserData getUserAndUserData(String nick);
    UserProfileData getUserProfileData(int IdUser);
    PhotoData getPhotoData(int userId);
    void changePhoto(int userId, PhotoData photoData);
}
