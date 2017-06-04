package com.walker.controller;

import com.walker.core.entities.User;
import com.walker.core.entities.UserData;
import com.walker.DataBaseControl.ControlUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Rafal on 23.05.2017.
 */
@Controller
public class EditControler {

    /*ControlUser controlUser;

    public EditControler()
    {
        controlUser = new ControlUser();
    }

    @RequestMapping(value = "/updateData")
    public String updateData(Model model,
                             @RequestParam(required = false, value = "firstName") String firstName,
                             @RequestParam(required = false, value = "lastName") String lastName,
                             @RequestParam(required = false, value = "city") String city) {

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        int IDUser = controlUser.getUserID(currentUser);

        controlUser.updateUserData(
                IDUser,
                new UserData(IDUser, firstName, lastName, city ,"12-12-12"));

        sendUserAndUserDataFromNick(model,currentUser);
        return "profile-edit";
    }

    @RequestMapping(value = "/updateMail")
    public String updateMail(Model model,
                             @RequestParam(required = false, value = "mail") String mail) {

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = controlUser.getUser(currentUser);

        user.setMail(mail);
        controlUser.updateUser(user.getUser_id(), user);

        sendUserAndUserDataFromNick(model,currentUser);
        return "profile-edit";
    }

    @RequestMapping(value = "/updatePassword")
    public String updatePassword(Model model,
                                 @RequestParam(required = false, value = "oldPassword") String oldPassword,
                                 @RequestParam(required = false, value = "newPassword") String newPassword,
                                 @RequestParam(required = false, value = "confirmPassword") String confirmPassword) {

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = controlUser.getUser(currentUser);

        if(user.getPassword().equals(oldPassword))
        {
            if(newPassword.equals(confirmPassword))
            {
                user.setPassword(newPassword);
                controlUser.updateUser(user.getUser_id(), user);
                sendUserAndUserDataFromNick(model,SecurityContextHolder.getContext().getAuthentication().getName());
                model.addAttribute("message", "hasło zostało zmienione");
            }
            else
            {
                model.addAttribute("message", "niepoprawne potwierdzenie hasla");
            }

        }
        else {
            model.addAttribute("message", "niepoprawne haslo");
        }

        return "message";
    }



    @RequestMapping(value = "/Profile-edit")
    public String profileEdit(Model model) {

        sendUserAndUserDataFromNick(model,SecurityContextHolder.getContext().getAuthentication().getName());
        return "profile-edit";
    }

    private void sendUserAndUserDataFromNick(Model model, String nick)
    {
        User user = controlUser.getUser(nick);
        UserData userData = controlUser.getUserData(user.getUser_id());

        model.addAttribute("ID", userData.getUserId());
        model.addAttribute("mail", user.getMail());
        model.addAttribute("nick", user.getNick());
        model.addAttribute("firstName", userData.getFirstName());
        model.addAttribute("lastName", userData.getLastName());
        model.addAttribute("City", userData.getCity());
    }*/
}
