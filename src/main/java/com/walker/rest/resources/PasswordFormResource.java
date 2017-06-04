package com.walker.rest.resources;

import com.walker.core.entities.PasswordForm;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Rafal on 04.06.2017.
 */
public class PasswordFormResource extends ResourceSupport {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public PasswordForm toPasswordForm()
    {
        PasswordForm result = new PasswordForm();
        result.setConfirmPassword(this.confirmPassword);
        result.setNewPassword(this.newPassword);
        result.setOldPassword(this.oldPassword);
        return result;
    }

}
