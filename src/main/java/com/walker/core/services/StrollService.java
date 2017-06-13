package com.walker.core.services;

import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.StrollData;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public interface StrollService {
    void editStroll(StrollData strollData) throws NoUserException, WrongLocationException;
    void addStroll(StrollData strollData) throws NoUserException, WrongLocationException, NotFoundException;
    void deleteStroll(int strollId) throws NoUserException, WrongLocationException;
}
