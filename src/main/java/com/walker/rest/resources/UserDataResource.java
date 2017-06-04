package com.walker.rest.resources;

import com.walker.core.entities.UserData;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Rafal on 04.06.2017.
 */
public class UserDataResource extends ResourceSupport {
    private String firstName;
    private String lastName;
    private String city;
    private String date;

    public UserDataResource(String firstName, String lastName, String city, String date) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.date = date;
    }

    public UserDataResource() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public UserData toUserData()
    {
        return new UserData(
                0,
                this.firstName,
                this.lastName,
                this.city,
                this.date
        );
    }

}
