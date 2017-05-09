package com.walker.DataBase;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class UserLogin {

    private String nick;
    private String password;
    private String mail;

    public UserLogin(String nick, String password, String mail) {

        this.nick = nick;
        this.password = password;
        this.mail = mail;
    }

    public UserLogin()
    {
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}