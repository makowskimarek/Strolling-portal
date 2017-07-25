package com.walker.core.services.impl;

import com.walker.DataBaseControl.ControlStroll;
import com.walker.DataBaseControl.ControlUser;
import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.StrollData;
import com.walker.core.services.StrollService;

import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class StrollServiceImpl implements StrollService {

    private ControlStroll controlStroll;
    private ControlUser controlUser;

    public StrollServiceImpl()
    {
        controlStroll = new ControlStroll();
        controlUser = new ControlUser();
    }

    @Override
    public void editStroll(StrollData strollData) throws NoUserException, WrongLocationException {
        controlStroll.updateStroll(strollData);
    }

    @Override
    public void addStroll(StrollData strollData) throws NoUserException, WrongLocationException, NotFoundException {
        controlStroll.addStroll(strollData);
    }

    @Override
    public void deleteStroll(int strollId) throws NoUserException, WrongLocationException {
        controlStroll.deleteStroll(strollId);
    }

    @Override
    public StrollData getStrollById(int strollId) throws NoUserException, WrongLocationException, NotFoundException {
        return controlStroll.getStrollById(strollId);
    }

    @Override
    public List<StrollData> getStrollByUserId(int userId) throws NoUserException, WrongLocationException, NotFoundException {
        return controlStroll.getStrollsByUserId(userId);
    }

    @Override
    public int getUserIdFromNick(String nick) {
        return controlUser.getUserID(nick);
    }
}
