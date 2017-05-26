package com.walker.DataBase;

/**
 * Created by Rafal on 25.04.2017.
 */

import java.util.Date;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 * @author pankaj
 */
public class UserData {

    private int user_id;
    private String firstName;
    private String lastName;
    private String city;
    private Date date;

    public UserData(int user_id, String firstName, String lastName, String city) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public UserData() {
    }



    public int getUserId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String country) {
        this.city = country;
    }

    @Override
    public String toString() {
        return "id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + city;
    }
}
