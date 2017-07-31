package com.walker.DataBaseControl;

import com.walker.core.entities.PhotoData;
import com.walker.core.entities.ProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rafal on 31.07.2017.
 */
public class ControlProfile {

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



    public ControlProfile() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);
    }

    public ProfileData getPhotoData(int userId)
    {
        SQL_SELECT =
                "SELECT * " +
                        "FROM user_profile " +
                        "WHERE user_id = ? ";

        List<ProfileData> profileList = jdbcTemplate.query(SQL_SELECT, this::mapProfileData,
                userId);

        if (profileList.size() == 0)
            return null;
        else
            return profileList.get(0);
    }

    public void addProfileData(int userId, ProfileData profileData)
    {
        SQL_INSERT =
                "INSERT INTO user_profile (user_id, photo_id, description,location_id) VALUES (?, ?, ? ,?)";
        jdbcTemplate.update(SQL_INSERT,
                userId,
                profileData.getPhotoId(),
                profileData.getDescription(),
                profileData.getLocationId());
    }

    public void updateDescription(int profileId, String description)
    {
        SQL_UPDATE = "UPDATE user_profile " +
                "SET description = ? " +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                description,
                profileId);
    }

    public void updateLocationid(int profileId, int locationId)
    {
        SQL_UPDATE = "UPDATE user_profile " +
                "SET location_id = ? " +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                locationId,
                profileId);
    }

    public void updatePhotoid(int profileId, int photoId)
    {
        SQL_UPDATE = "UPDATE user_profile " +
                "SET photo_id = ? " +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                photoId,
                profileId);
    }

    private ProfileData mapProfileData(ResultSet rs, int row)
            throws SQLException {

        return new ProfileData(
                rs.getInt("user_id"),
                rs.getInt("photo_id"),
                rs.getString("description"),
                rs.getInt("location_id")

        );
    }
}
