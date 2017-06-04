package com.walker.controller;

import com.google.gson.Gson;
import com.walker.DataBase.*;
import com.walker.DataBaseControl.ControlUser;
import com.walker.core.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swiety on 21.03.2017.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Model model) {

        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        if(currentUser.equals("anonymousUser"))
        {
            return "index";
        }
        else
        {
            ControlUser controlUser = new ControlUser();
            User user = controlUser.getUser(currentUser);
            UserData userData = controlUser.getUserData(user.getUser_id());

            model.addAttribute("ID", userData.getUserId());
            model.addAttribute("firstName", userData.getFirstName());
            model.addAttribute("lastName", userData.getLastName());
            model.addAttribute("City", userData.getCity());

            return "profile";
        }

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
        model.addAttribute("firstName", userData.getFirstName());
        model.addAttribute("lastName", userData.getLastName());
        model.addAttribute("City", userData.getCity());

        return "profile";
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

    /*@RequestMapping(value = "/Register", method= RequestMethod.POST)
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
            UserData userData = new UserData(idUser,firstName,lastName,city, "12-12-12");
            controlUser.addUserData(userData);

            model.addAttribute("message", "Rejestracja udana");
        }else {
            model.addAttribute("message", "Osoba o podanym nicku już istnieje");
        }

        return "message";
    }*/


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

            UserData userData = new UserData(user_id, "firstname", "surname", "city", "12-12-12");
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

    @RequestMapping(value = "/GetPersonsAndroid", method= RequestMethod.POST)
    public @ResponseBody
    String getPersonsAndroid(@RequestBody String jsonString)
    {
        Gson gson = new Gson();
        SearchCriteria searchCriteria = gson.fromJson(jsonString, SearchCriteria.class);

        ControlUser controlUser = new ControlUser();

        List<UserRange> usersRange = controlUser.getUsersByCriteries(searchCriteria.getUserLatitude(), searchCriteria.getUserLongtitude(),
                searchCriteria.getDistance(), searchCriteria.getAgeFrom(), searchCriteria.getAgeTo());

        List<UserProfileData> userProfileDataList = new ArrayList<UserProfileData>();
        for (UserRange userRange : usersRange) {
            userProfileDataList.add(controlUser.getUserProfileData(userRange.getUserId()));
        }
        return gson.toJson(userProfileDataList);
    }


}