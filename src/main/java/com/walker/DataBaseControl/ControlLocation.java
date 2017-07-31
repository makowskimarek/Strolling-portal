package com.walker.DataBaseControl;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.LocationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rafal on 31.07.2017.
 */
public class ControlLocation {

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



    public ControlLocation() {
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

    public void updateLocation(int idLocation, LocationData data)
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
                idLocation);
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
