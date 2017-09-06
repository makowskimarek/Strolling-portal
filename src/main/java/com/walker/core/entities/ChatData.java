package com.walker.core.entities;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class ChatData {
    private int userId;
    private String firstName;
    private String lastName;
    private byte[] personPhoto;


    public ChatData() {
    }


    public ChatData(int userId, String firstName, String lastName, byte[] personPhoto) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personPhoto = personPhoto;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(byte[] personPhoto) {
        this.personPhoto = personPhoto;
    }
}
