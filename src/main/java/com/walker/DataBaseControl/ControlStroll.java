package com.walker.DataBaseControl;

import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */
public class ControlStroll {

    /**
     * Query of SQL
     */
    private String SQL_INSERT;
    private String SQL_SELECT;
    private String SQL_UPDATE;

    /**
     * Object of JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ControlStroll() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void addStroll(StrollData strollData) throws NoUserException, WrongLocationException, NotFoundException {
        checkAdInStroll(strollData.getAd_id());
        checkAdInAd(strollData.getAd_id());
        if (strollData.getUsers().length < 2 || strollData.getUsers()[0] < 1 || strollData.getUsers()[1] < 1) {
            throw new NoUserException();
        } else {
            SQL_INSERT =
                    "INSERT INTO stroll " +
                            "(location_id, info, data_start, data_end, status, ad_id, privacy) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?) ";

            jdbcTemplate.update(SQL_INSERT,
                    strollData.getLocationId(),
                    "info",
                   null,
                    null,
                    "activ",
                    strollData.getAd_id(),
                    strollData.getPrivacy());

            addParticipant(strollData.getAd_id(), strollData.getUsers()[0]);
            addParticipant(strollData.getAd_id(), strollData.getUsers()[1]);
        }
    }

    public void checkAdInStroll(int iD) throws NotFoundException {
        SQL_SELECT = "SELECT * " +
                "FROM stroll str " +
                "WHERE str.ad_id = ? ";

        List<AdvertisementData> adList = jdbcTemplate.query(SQL_SELECT, this::mapAdvertisementData, iD);

        if(adList.size()>0){
            throw new NotFoundException();
        }
    }

    public void checkAdInAd(int iD) throws NotFoundException {
        SQL_SELECT = "SELECT * " +
                "FROM advertisement a " +
                "WHERE a.ad_id = ? ";

        List<AdvertisementData> adList = jdbcTemplate.query(SQL_SELECT, this::mapAdvertisementData, iD);

        if(adList.size()<0){
            throw new NotFoundException();
        }
    }

    public void editStroll(StrollData strollData) throws NoUserException, WrongLocationException {
        if (strollData.getUsers().length < 3 || strollData.getUsers()[0] < 1 || strollData.getUsers()[1] < 1) {
            throw new NoUserException();
        } else {
            SQL_UPDATE = "UPDATE stroll " +
                    "SET location_id = ?, info = ?, data_start = ? , data_end = ?, status = ?" +
                    "WHERE user_id = ?";

            jdbcTemplate.update(SQL_UPDATE,
                    strollData.getLocationId(),
                    strollData.getInfo(),
                    strollData.getData_start(),
                    strollData.getData_end(),
                    strollData.getStatus());
        }
    }

    public void addParticipant(int ad_id, int userId) {
        SQL_INSERT = "INSERT INTO participants " +
                "(stroll_id, user_id) " +
                "VALUES ((SELECT (str.stroll_id) FROM stroll str WHERE str.ad_id = ?),?)";

        jdbcTemplate.update(SQL_INSERT,
                ad_id,
                userId);
    }

    public void addLocation(double latitude, double longtitude, String locationName) {
        SQL_INSERT =
                "INSERT INTO location " +
                        "(latitude, longtitude, description) " +
                        "VALUES (?, ?, ?)";

        jdbcTemplate.update(SQL_INSERT,
                latitude,
                longtitude,
                locationName);
    }

    public int getLocationId(double latitude, double longtitude) throws WrongLocationException {
        SQL_SELECT =
                "SELECT * " +
                        "FROM location l " +
                        "WHERE l.latitude = ? AND l.longtitude = ?";

        List<LocationData> locationList = jdbcTemplate.query(SQL_SELECT, this::mapLocationData,
                latitude, longtitude);
        if (locationList.get(0) == null) {
            throw new WrongLocationException();
        }
        return locationList.get(0).getLocation_id();
    }

    public void deleteStroll(int strollId) {
    }

    private LocationData mapLocationData(ResultSet rs, int row)
            throws SQLException {
        return new LocationData(
                rs.getInt("location_id"),
                rs.getDouble("latitude"),
                rs.getDouble("longtitude"),
                rs.getString("description")
        );
    }

    private ParticipantsData mapParticipantsData(ResultSet rs, int row)
            throws SQLException {
        return new ParticipantsData(
                rs.getInt("user_id"),
                rs.getInt("stroll_id"),
                rs.getInt("rate"),
                rs.getString("comment")
        );
    }

    private LastInsertIdData mapLastInsertIdData(ResultSet rs, int row)
            throws SQLException {
        return new LastInsertIdData(
                rs.getInt("LAST_INSERT_ID()"));
    }

    private AdvertisementData mapAdvertisementData(ResultSet rs, int row) throws SQLException
    {
        return new AdvertisementData(
                rs.getInt("ad_id"),
                rs.getInt("user_id"),
                null,
                rs.getString("description"),
                rs.getString("stroll_starttime"),
                rs.getString("stroll_endtime"),
                rs.getString("ad_endtime"),
                rs.getString("privacy")
        );
    }

}
