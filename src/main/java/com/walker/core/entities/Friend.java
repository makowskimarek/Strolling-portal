package com.walker.core.entities;

/**
 * Created by Rafal on 16.06.2017.
 */
public class Friend {

    private int user1Id;
    private int user2Id;

    public Friend() {
    }

    public Friend(int user1Id, int user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }


}
