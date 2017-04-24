package com.walker.controller;

//import com.walker.DAO.PersonDAO;
import com.walker.DataBase.Person;
import com.walker.DataBaseControl.JdbcWalkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //@ResponseBody
    @RequestMapping(value = "/Register")
    public String register(Model model,
                           @RequestParam(required = false, value = "firstName") String firstName,
                           @RequestParam(required = false, value = "lastName") String lastName,
                           @RequestParam(required = false, value = "city") String city) {


        JdbcWalkerRepository walkerRepository = new JdbcWalkerRepository();

        Person person = new Person();
        person.setName(firstName);
        person.setSurname(lastName);
        person.setCity(city);
        person.setDate(new Date());

        walkerRepository.addPerson(person);


        return "home";
    }
}