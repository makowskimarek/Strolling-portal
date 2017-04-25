package com.walker.DataBase;

/**
 * Created by Rafal on 25.04.2017.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 * @author pankaj
 */
public class UserData {

    private int user_id;

    private String name;

    private String surname;

    private String city;

    public UserData(int user_id, String name, String surname, String city) {
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.city = city;
    }

    public UserData()
    {

    }

    public int getUserId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String country) {
        this.city = country;
    }

    @Override
    public String toString() {
        return "id=" + user_id + ", name=" + name + ", surname=" + surname + ", country=" + city;
    }
}
