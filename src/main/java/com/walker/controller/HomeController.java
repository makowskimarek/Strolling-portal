package com.walker.controller;

import com.walker.DataBase.User;
import com.walker.DataBaseControl.ControlUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Swiety on 21.03.2017.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Model model) {

        return "index";
    }

    @RequestMapping(value = "/Login")
    public String login(Model model) {

        return "login";
    }

    @RequestMapping(value = "/Meeting")
    public String meeting(Model model) {

        return "meeting";
    }

    @RequestMapping(value = "/Announce")
    public String announce(Model model) {

        return "announce";
    }

    @RequestMapping(value = "/Profile")
    public String profile(Model model) {

        return "profile";
    }

    @RequestMapping(value = "/Profile-edit")
    public String profileEdit(Model model) {

        return "profile-edit";
    }

    @RequestMapping(value = "/Friends")
    public String friends(Model model) {

        return "friends";
    }

    @RequestMapping(value = "/AnnounceTest")
    public String annoutanceTest(Model model) {

        return "staremiasto";
    }

    @RequestMapping(value = "/Main")
    public String mainn(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        ControlUser controlUser = new ControlUser();
        User user;

        return "main";
    }

    @RequestMapping(value = "/Register", method= RequestMethod.POST)
    public String register(Model model,
                           @RequestParam(required = false, value = "nick") String nick,
                           @RequestParam(required = false, value = "password") String password,
                           @RequestParam(required = false, value = "mail") String mail) {



        ControlUser controlUser = new ControlUser();

        User user = controlUser.getUser(nick);
        if(user == null)
        {
            user = new User();
            user.setNick(nick);
            user.setPassword(password);
            user.setMail(mail);
            controlUser.addUser(user);
            model.addAttribute("message", "Rejestracja udana");

        }else {
            model.addAttribute("message", "Osoba o podanym nicku już istnieje");
        }

        return "message";
    }
}