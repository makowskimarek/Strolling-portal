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

    @RequestMapping(value = "/Profile")
    public String start(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        ControlUser controlUser = new ControlUser();
        User user;

        return "start";
    }

    @RequestMapping(value = "/Register", method= RequestMethod.POST)
    public String register(Model model,
                           @RequestParam(required = false, value = "nick") String nick,
                           @RequestParam(required = false, value = "password") String password,
                           @RequestParam(required = false, value = "mail") String mail) {


        ControlUser controlUser = new ControlUser();

        User user = new User();
        user.setNick(nick);
        user.setPassword(password);
        user.setMail(mail);
        

        controlUser.addUser(user);

        model.addAttribute("message", "Rejestracja udana");

        return "message";
    }
}