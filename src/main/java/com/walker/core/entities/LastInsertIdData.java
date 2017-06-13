package com.walker.core.entities;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class LastInsertIdData {
    private int LAST_INSERT_ID;

    public LastInsertIdData() {
    }

    public LastInsertIdData(int LAST_INSERT_ID) {
        this.LAST_INSERT_ID = LAST_INSERT_ID;
    }

    public int getLAST_INSERT_ID() {
        return LAST_INSERT_ID;
    }

    public void setLAST_INSERT_ID(int LAST_INSERT_ID) {
        this.LAST_INSERT_ID = LAST_INSERT_ID;
    }
}
