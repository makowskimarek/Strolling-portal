package com.walker.controller;

import com.walker.DataBase.User;
import com.walker.DataBaseControl.JdbcWalkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.walker.DataBase.UserData;

import java.util.Date;

/**
 * Created by Swiety on 21.03.2017.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Model model) {


        return "home";
    }

    @RequestMapping(value = "/Start")
    public String start(Model model) {
/*
        //do wypełnienia przez ciebie


        PersonDAO personDAO = context.getBean(PersonDAO.class);

        Person person = new Person();
        person.setName("Jan");
        person.setSurname("AAAAA");
        person.setCity("Sosnowiec");
        person.setDate(new Date());


        personDAO.save(person);

        System.out.println("Person::" + person);

        List<Person> list = personDAO.list();

        PersonStructure example = new PersonStructure();
        PersonBase personBase = new PersonBase();

        for (Person p : list) {
//            System.out.println("Person List::" + p);
            example.setFirstName(p.getName())
                    .setLastName(p.getSurname())
                    .setCity(p.getCity())
                    .setDate(p.getDate());
            personBase.personBase.add(example);
        }
        //close resources
        context.close();

        model.addAttribute("personBase", personBase.takeAll());
*/
        return "start";
    }

    @RequestMapping(value = "/Register", method= RequestMethod.POST)
    public String register(Model model,
                           @RequestParam(required = false, value = "firstName") String firstName,
                           @RequestParam(required = false, value = "lastName") String lastName,
                           @RequestParam(required = false, value = "city") String city,
                           @RequestParam(required = false, value = "nick") String nick,
                           @RequestParam(required = false, value = "password") String password,
                           @RequestParam(required = false, value = "mail") String mail) {


        JdbcWalkerRepository walkerRepository = new JdbcWalkerRepository();

        User user = new User();
        user.setNick(nick);
        user.setPassword(password);
        user.setMail(mail);

        UserData userData = new UserData();
        userData.setName(firstName);
        userData.setSurname(lastName);
        userData.setCity(city);

        walkerRepository.register(userData, user);


        return "home";
    }
}