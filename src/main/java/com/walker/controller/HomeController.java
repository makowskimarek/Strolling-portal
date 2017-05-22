package com.walker.controller;

import com.google.gson.Gson;
import com.walker.DataBase.User;
import com.walker.DataBase.UserData;
import com.walker.DataBase.UserLogin;
import com.walker.DataBase.UserRegister;
import com.walker.DataBaseControl.ControlUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        ControlUser controlUser = new ControlUser();
        User user = controlUser.getUser(currentUser);
        UserData userData = controlUser.getUserData(user.getUser_id());

        model.addAttribute("ID", userData.getUserId());
        model.addAttribute("firstName", userData.getName());
        model.addAttribute("lastName", userData.getLastName());
        model.addAttribute("City", userData.getCity());

        return "profile";
    }

    @RequestMapping(value = "/Profile-edit")
    public String profileEdit(Model model) {

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        ControlUser controlUser = new ControlUser();
        User user = controlUser.getUser(currentUser);
        UserData userData = controlUser.getUserData(user.getUser_id());

        model.addAttribute("ID", userData.getUserId());
        model.addAttribute("mail", user.getMail());
        model.addAttribute("nick", user.getNick());
        model.addAttribute("firstName", userData.getName());
        model.addAttribute("lastName", userData.getLastName());
        model.addAttribute("City", userData.getCity());

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
        
        return "main";
    }

    @RequestMapping(value = "/Register", method= RequestMethod.POST)
    public String register(Model model,
                           @RequestParam(required = false, value = "nick") String nick,
                           @RequestParam(required = false, value = "password") String password,
                           @RequestParam(required = false, value = "mail") String mail,
                           @RequestParam(required = false, value = "firstName") String firstName,
                           @RequestParam(required = false, value = "lastName") String lastName,
                           @RequestParam(required = false, value = "city") String city) {
        
        ControlUser controlUser = new ControlUser();

        User user = controlUser.getUser(nick);
        if(user == null)
        {
            user = new User();
            user.setNick(nick);
            user.setPassword(password);
            user.setMail(mail);
            controlUser.addUser(user);

            int idUser = controlUser.getUserID(nick);
            UserData userData = new UserData(idUser,firstName,lastName,city);
            controlUser.addUserData(userData);

            model.addAttribute("message", "Rejestracja udana");
        }else {
            model.addAttribute("message", "Osoba o podanym nicku już istnieje");
        }

        return "message";
    }

    @RequestMapping(value = "/RegisterAndroid", method= RequestMethod.POST)
    public @ResponseBody
    String register(@RequestBody String jsonString)
    {
        Gson gson = new Gson();
        UserRegister userRegister = gson.fromJson(jsonString, UserRegister.class);

        ControlUser controlUser = new ControlUser();

        User user = new User(userRegister);

        if(controlUser.addUser(user)){
            int user_id = controlUser.getUserID(user.getNick());

            UserData userData = new UserData(user_id, "firstname", "surname", "city");
            controlUser.addUserData(userData);

            return gson.toJson(userData);
        }else{
            return "{\"error\":\"duplicateUser\"}";
        }
    }

    @RequestMapping(value = "/LoginAndroid", method= RequestMethod.POST)
    public @ResponseBody
    String login(@RequestBody String jsonString)
    {
        jsonString.lastIndexOf("nick");
        Gson gson = new Gson();
        UserLogin userLogin = gson.fromJson(jsonString, UserLogin.class);

        ControlUser controlUser = new ControlUser();

        int user_id = controlUser.getUserID(userLogin.getNick());
        if(user_id ==-1){
            return "{\"error\":\"notAnUser\"}";
        }else{
            return "{\"user_id\":\"" + user_id + "\"}";
        }
    }
}