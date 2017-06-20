package com.walker.DataBaseControl;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.AdvertisementData;
import com.walker.core.entities.LocationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rafal on 09.06.2017.
 */
@Repository
public class ControlAdvertisement {

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

    public ControlAdvertisement() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);
    }

    public int getLastLocationId()
    {
        SQL_SELECT = "SELECT MAX(location_id)" +
                "FROM location";

        return jdbcTemplate.queryForObject(SQL_SELECT, Integer.class);
    }


    public LocationData getLocationDataById(int iD) throws NotFoundException
    {
        SQL_SELECT = "SELECT * " +
                "FROM location l " +
                "WHERE l.location_id = ? ";

        List<LocationData> locationList = jdbcTemplate.query(SQL_SELECT, this::mapLocationData, iD);
        if(locationList.size()==0){
            throw new NotFoundException();
        }

        return locationList.get(0);
    }


    public int setLocationData(LocationData data)
    {
        SQL_INSERT =
                "INSERT INTO location (latitude, longtitude, description) VALUES (?, ? ,?)";

        jdbcTemplate.update(SQL_INSERT,
                data.getLatitude(),
                data.getLongtitude(),
                data.getDescription());

        return getLastLocationId();
    }

    public void updateLocation(LocationData data)
    {
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

    public AdvertisementData getAdvertisementData(int iD) throws NotFoundException {
        SQL_SELECT = "SELECT * " +
                "FROM advertisement a " +
                "WHERE a.ad_id = ? ";

        List<AdvertisementData> locationList = jdbcTemplate.query(SQL_SELECT, this::mapAdvertisementData, iD);

        if(locationList.size()==0){
            throw new NotFoundException();
        }

        return locationList.get(0);
    }


    public int getLastAdvertisementId()
    {
        SQL_SELECT = "SELECT MAX(ad_id)" +
                "FROM advertisement";

        return jdbcTemplate.queryForObject(SQL_SELECT, Integer.class);
    }

    public void updateAdvertisementData(AdvertisementData data) throws NotFoundException
    {
        updateLocation(data.getLocation());
        AdvertisementData advertisementData = getAdvertisementData(data.getAdId());

        if(advertisementData.getUserId() != data.getUserId())
            throw new NotFoundException();

        SQL_UPDATE = "UPDATE advertisement " +
                "SET " +
                "user_id = ?, " +
                "location_id = ?, " +
                "description = ?," +
                "stroll_starttime = ?," +
                "stroll_endtime = ?," +
                "ad_endtime = ?," +
                "privacy = ? " +
                "WHERE ad_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                data.getUserId(),
                data.getLocation().getLocation_id(),
                data.getDescription(),
                data.getStrollStartTime(),
                data.getStrollEndTime(),
                data.getAdEndTime(),
                data.getPrivacy(),
                data.getAdId());


    }

    public void deleteAdvertisementData(int iD)
    {
        SQL_DELETE = "DELETE FROM advertisement " +
                "WHERE ad_id = ?";

        jdbcTemplate.update(SQL_DELETE, iD);
    }

    public int setAdvertisementData(AdvertisementData data)
    {
        int idLocation = setLocationData(data.getLocation());

        SQL_INSERT =
                "INSERT INTO advertisement (" +
                        "user_id, " +
                        "location_id, " +
                        "description," +
                        "stroll_starttime," +
                        "stroll_endtime," +
                        "ad_endtime," +
                        "privacy" +
                        ") VALUES (?, ? ,?, ?, ?, ?, ?)";

        jdbcTemplate.update(SQL_INSERT,
                data.getUserId(),
                idLocation,
                data.getDescription(),
                data.getStrollStartTime(),
                data.getStrollEndTime(),
                data.getAdEndTime(),
                data.getPrivacy());

        data.getLocation().setLocation_id(idLocation);

        return getLastAdvertisementId();
    }

    public List<AdvertisementData> getUserAdvertisement(int userId){
        SQL_SELECT =
                "SELECT * " +
                        "FROM advertisement a " +
                        "WHERE a.user_id = ? ";

        List<AdvertisementData> list = jdbcTemplate.query(SQL_SELECT, this::mapAdvertisementData,
                userId);

        return list;
    }

    public List<AdvertisementData> getAdvertisementwithPrivacy(String privacy)
    {
        SQL_SELECT =
                "SELECT * " +
                        "FROM advertisement a " +
                        "WHERE a.privacy like ? ";

        List<AdvertisementData> list = jdbcTemplate.query(SQL_SELECT, this::mapAdvertisementData,
                privacy);

        return list;
    }

    private AdvertisementData mapAdvertisementData(ResultSet rs, int row) throws SQLException
    {
        LocationData location = null;
        try {
            location = getLocationDataById(rs.getInt("location_id"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return new AdvertisementData(
                rs.getInt("ad_id"),
                rs.getInt("user_id"),
                location,
                rs.getString("description"),
                rs.getString("stroll_starttime"),
                rs.getString("stroll_endtime"),
                rs.getString("ad_endtime"),
                rs.getString("privacy")
        );
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
}
