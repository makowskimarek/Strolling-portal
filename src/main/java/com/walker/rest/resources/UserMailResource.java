package com.walker.rest.resources;

import com.walker.core.entities.UserMail;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Rafal on 04.06.2017.
 */
public class UserMailResource extends ResourceSupport {
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserMail toUserMail()
    {
        UserMail result = new UserMail();
        result.setMail(this.mail);
        return result;
    }

}
