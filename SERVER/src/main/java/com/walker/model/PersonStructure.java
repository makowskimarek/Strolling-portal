package com.walker.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Rafal on 21.03.2017.
 */
@Component
public class PersonStructure {

    @NotNull
    @Length(min = 5, max = 20)
    private String firstName;

    @NotNull
    @Length(min = 5, max = 20)
    private String lastName;

    @NotNull
    @Length(min = 4, max = 30)
    private String city;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy.mm.dd")
    private Date date;

    public PersonStructure setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public PersonStructure setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public PersonStructure setCity(String city)
    {
        this.city = city;
        return this;
    }

    public PersonStructure setDate(Date data)
    {
        this.date = data;
        return this;
    }

    public Date getData()
    {
        return date;
    }

    public String to_String()
    {
        return firstName + " " + lastName + " " + city + date.toString();
    }
}
