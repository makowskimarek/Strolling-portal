package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlStroll;
import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.StrollData;
import com.walker.core.services.StrollService;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class StrollServiceImpl implements StrollService {

    private ControlStroll controlStroll;

    public StrollServiceImpl()
    {
        controlStroll = new ControlStroll();
    }

    @Override
    public void editStroll(StrollData strollData) throws NoUserException, WrongLocationException {
        controlStroll.editStroll(strollData);
    }

    @Override
    public void addStroll(StrollData strollData) throws NoUserException, WrongLocationException, NotFoundException {
        controlStroll.addStroll(strollData);
    }

    @Override
    public void deleteStroll(int strollId) throws NoUserException, WrongLocationException {
        controlStroll.deleteStroll(strollId);
    }
}
