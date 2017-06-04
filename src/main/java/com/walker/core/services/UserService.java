package com.walker.core.services;

import com.walker.core.entities.*;

/**
 * Created by Rafal on 03.06.2017.
 */
public interface UserService {

    public int getUserIdFromNick(String nick);
    public RegistrationForm addUserRegistration(RegistrationForm form);
    public void updateUserData(int userId,UserData userData);
    public void updateUserMail(int userId, UserMail userMail);
    public void updatePassword(int userId, PasswordForm passwordForm);
    public UserAndUserData getUserAndUserData(int userId);
    public UserAndUserData getUserAndUserData(String nick);
    public UserProfileData getUserProfileData(int IdUser);
}
