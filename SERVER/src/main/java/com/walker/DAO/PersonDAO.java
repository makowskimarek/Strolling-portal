package com.walker.DAO;

import com.walker.DataBase.Person;

import java.util.List;

public interface PersonDAO {

    public void save(Person p);

    public List<Person> list();

}