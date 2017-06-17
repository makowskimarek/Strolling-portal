package com.walker.DataBaseControl;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.Friend;
import com.walker.core.entities.UserData;
import com.walker.core.entities.UserProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rafal on 16.06.2017.
 */
@Repository
public class ControlFriend {

    /**
     * Query of SQL
     */
    private String SQL_INSERT =
            "INSERT INTO friends (user1_id, user2_id) VALUES (?, ?)";

    private String SQL_SELECT =
            "SELECT * " +
            "FROM friends f " +
            "WHERE f.user1_id = ? OR f.user2_id = ?";

    private String SQL_DELETE =
            "DELETE FROM friends f " +
            "WHERE f.user1_id = ? AND f.user2_id = ?";

    /**
     * Object of JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ControlFriend()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void addFriend(Friend friend)
    {
        jdbcTemplate.update(SQL_INSERT,
                friend.getUser1Id(),
                friend.getUser2Id());
    }

    public List<Friend> getUserFriends(int idUser) throws NotFoundException
    {
        List<Friend> list = jdbcTemplate.query(SQL_SELECT,this::mapFriendData,
                idUser,idUser);

        if (list.size() == 0) throw new NotFoundException();

        return list;
    }

    public void deleteFriends(Friend friend)
    {
        jdbcTemplate.update(SQL_DELETE,
                friend.getUser1Id(),
                friend.getUser2Id());
    }

    private Friend mapFriendData(ResultSet rs, int row)
            throws SQLException {

        return new Friend(
                rs.getInt("user1_id"),
                rs.getInt("user2_id"));

    }



}
