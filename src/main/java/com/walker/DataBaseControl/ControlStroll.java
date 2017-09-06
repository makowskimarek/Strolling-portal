package com.walker.DataBaseControl;

import com.walker.DataBaseControl.databaseException.NoUserException;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.LocationData;
import com.walker.core.entities.ParticipantsData;
import com.walker.core.entities.StrollData;
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
    private String SQL_DELETE;

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

    public void addStroll(StrollData strollData, int currentUserId) throws NoUserException, WrongLocationException, NotFoundException {
        SQL_INSERT =
                "INSERT INTO stroll " +
                        "(location_id, info, data_start, data_end, status, privacy) " +
                        "VALUES (?, ?, ?, ?, ?, ?) ";

        jdbcTemplate.update(SQL_INSERT,
                strollData.getLocation().getLocation_id(),
                strollData.getInfo(),
                strollData.getData_start(),
                strollData.getData_end(),
                strollData.getStatus(),
                strollData.getPrivacy());
        int lastStrollId = getLastStrollId();
        addParticipant(lastStrollId, currentUserId);
        addParticipant(lastStrollId, strollData.getUser());

    }

    public void updateStroll(StrollData strollData) throws NoUserException, WrongLocationException {
        updateLocation(strollData.getLocation());

        SQL_UPDATE = "UPDATE stroll " +
                "SET location_id = ?, info = ?, data_start = ? , data_end = ?, status = ? " +
                "WHERE stroll_id = ?";

        int i = jdbcTemplate.update(SQL_UPDATE,
                strollData.getLocation().getLocation_id(),
                strollData.getInfo(),
                strollData.getData_start(),
                strollData.getData_end(),
                strollData.getStatus(),
                strollData.getStrollId());
    }

    public void deleteStroll(int strollId) {
        SQL_DELETE = "DELETE FROM stroll " +
                "WHERE stroll_id = ?";

        jdbcTemplate.update(SQL_DELETE, strollId);
    }

    public StrollData getStrollById(int strollId, int userId) throws NotFoundException {
        SQL_SELECT = "SELECT str.stroll_id, str.location_id, str.info, str.data_start, str.data_end, str.status, str.privacy, par.user_id " +
                "FROM ( " +
                    "SELECT str.stroll_id " +
                    "FROM stroll str " +
                    "INNER JOIN participants par1 " +
                    "ON str.stroll_id = par1.stroll_id " +
                    "WHERE par1.user_id = ? " +
                ") AS strid " +
                "INNER JOIN stroll str " +
                "ON strid.stroll_id = str.stroll_id " +
                "INNER JOIN participants par " +
                "ON str.stroll_id = par.stroll_id " +
                "WHERE par.user_id <> ? " +
                "AND str.Stroll_id = ? ";

        List<StrollData> strollDataList = jdbcTemplate.query(SQL_SELECT, this::mapStrollData,
                userId, userId, strollId);

        if (strollDataList.size() == 0) {
            throw new NotFoundException();
        }

        return strollDataList.get(0);
    }

    public List<StrollData> getStrollsByUserId(int userId) throws NotFoundException {
        SQL_SELECT = "SELECT str.stroll_id, str.location_id, str.info, str.data_start, str.data_end, str.status, str.privacy, par.user_id " +
        "FROM ( " +
                "SELECT str.stroll_id " +
                "FROM stroll str " +
                "INNER JOIN participants par1 " +
                "ON str.stroll_id = par1.stroll_id " +
                "WHERE par1.user_id = ? " +
        ") AS strid " +
        "INNER JOIN stroll str " +
        "ON strid.stroll_id = str.stroll_id " +
        "INNER JOIN participants par " +
        "ON str.stroll_id = par.stroll_id " +
        "WHERE par.user_id <> ? ";

        List<StrollData> strollDataList = jdbcTemplate.query(SQL_SELECT, this::mapStrollData,
                userId, userId);

        if (strollDataList.size() == 0) {
            throw new NotFoundException();
        }

        return strollDataList;
    }

    private int getLastStrollId() {
        SQL_SELECT = "SELECT MAX(stroll_id)" +
                "FROM stroll";

        return jdbcTemplate.queryForObject(SQL_SELECT, Integer.class);
    }

    private void updateLocation(LocationData data) {
        SQL_UPDATE = "UPDATE location " +
                "SET " +
                "latitude = ?, " +
                "longtitude = ?, " +
                "description = ? " +
                "WHERE location_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                data.getLatitude(),
                data.getLongtitude(),
                data.getDescription(),
                data.getLocation_id());
    }

    private LocationData getLocationDataById(int iD) throws NotFoundException {
        SQL_SELECT = "SELECT * " +
                "FROM location l " +
                "WHERE l.location_id = ? ";

        List<LocationData> locationList = jdbcTemplate.query(SQL_SELECT, this::mapLocationData, iD);
        if(locationList.size()==0){
            throw new NotFoundException();
        }

        return locationList.get(0);
    }

    private List<ParticipantsData> getParticipantsDataByStrollId(int strollId) throws NotFoundException {
        SQL_SELECT = "SELECT * " +
                "FROM participants par " +
                "WHERE par.stroll_id = ? ";

        List<ParticipantsData> participantsData = jdbcTemplate.query(SQL_SELECT, this::mapParticipantsData,
                strollId);

        if (participantsData.size() == 0) {
            throw new NotFoundException();
        }

        return participantsData;
    }

    private void addParticipant(int stroll_id, int userId) {
        SQL_INSERT = "INSERT INTO participants " +
                "(stroll_id, user_id) " +
                "VALUES (?,?) ";

        jdbcTemplate.update(SQL_INSERT,
                stroll_id,
                userId);
    }

    private LocationData mapLocationData(ResultSet rs, int row) throws SQLException {
        return new LocationData(
                rs.getInt("location_id"),
                rs.getDouble("latitude"),
                rs.getDouble("longtitude"),
                rs.getString("description")
        );
    }

    private ParticipantsData mapParticipantsData(ResultSet rs, int row) throws SQLException {
        return new ParticipantsData(
                rs.getInt("user_id"),
                rs.getInt("stroll_id"),
                rs.getInt("rate"),
                rs.getString("comment")
        );
    }

    private StrollData mapStrollData(ResultSet rs, int row) throws SQLException {
        LocationData location = null;
        try {
            location = getLocationDataById(rs.getInt("location_id"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return new StrollData(
                rs.getInt("stroll_id"),
                location,
                rs.getString("info"),
                rs.getString("data_start"),
                rs.getString("data_end"),
                rs.getString("status"),
                rs.getString("privacy"),
                rs.getInt("user_id")
        );
    }


}
