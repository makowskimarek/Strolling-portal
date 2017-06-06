package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.*;
import com.walker.core.services.UserService;
import com.walker.core.services.exception.PasswordException;
import com.walker.core.services.exception.UserExsistException;

/**
 * Created by Rafal on 03.06.2017.
 */
public class UserServiceImpl implements UserService {

    private ControlUser controlUser;

    public UserServiceImpl()
    {
        controlUser = new ControlUser();
    }

    @Override
    public int getUserIdFromNick(String nick) {
        return controlUser.getUserID(nick);
    }

    @Override
    public RegistrationForm addUserRegistration(RegistrationForm form) throws UserExsistException{
        int IdUser = -1;
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
        }
        else
            throw new UserExsistException();

        return form;
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
        return controlUser.getUserProfileData(userId);
    }

    @Override
    public void inviteUserToStroll(InvitationData invitationData) throws NoUserException, WrongLocationException {
        controlUser.inviteUser(invitationData);
    }
}
