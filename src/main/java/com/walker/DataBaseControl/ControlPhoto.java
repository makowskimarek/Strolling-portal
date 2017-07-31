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
public class ControlPhoto {

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



    public ControlPhoto() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);
    }

    public int getLastPhotoId()
    {
        SQL_SELECT = "SELECT MAX(photo_id)" +
                "FROM photos";

        return jdbcTemplate.queryForObject(SQL_SELECT, Integer.class);
    }

    public PhotoData getPhotoData(int photoId)
    {
        SQL_SELECT =
                "SELECT * " +
                        "FROM photos " +
                        "WHERE photo_id = ? ";

        List<PhotoData> photoList = jdbcTemplate.query(SQL_SELECT, this::mapPhotoData,
                photoId);

        if (photoList.size() == 0)
            return null;
        else
            return photoList.get(0);
    }

    public int addPhotoData(PhotoData photoData)
    {
        SQL_INSERT =
                "INSERT INTO photos (photo_url, took_time) VALUES (?, ?)";
        jdbcTemplate.update(SQL_INSERT,
                photoData.getData(),
                photoData.getTook_time());

        return getLastPhotoId();
    }

    public void updatePhotoData(int photoId, PhotoData photoData)
    {
        SQL_UPDATE = "UPDATE photos " +
                "SET photo_url = ?, took_time = ? " +
                "WHERE photo_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                photoData.getData(),
                photoData.getTook_time(),
                photoId);
    }



    private PhotoData mapPhotoData(ResultSet rs, int row)
            throws SQLException {

        return new PhotoData(
                rs.getInt("photo_id"),
                rs.getBytes("photo_url"),
                rs.getString("took_time")
        );
    }
}
