package com.walker.DataBaseControl;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.DataBaseControl.databaseException.WrongLocationException;
import com.walker.core.entities.NotificationData;
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
 * Created by Rafal on 09.06.2017.
 */
@Repository
public class ControlNotification {

    /**
            * Query of SQL
     */
    private String SQL_INSERT;
    private String SQL_SELECT;
    private String SQL_UPDATE;

    private ControlUser controlUser;

    /**
     * Object of JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ControlNotification() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);

        controlUser = new ControlUser();
    }

    public NotificationData getNotificationDataById(int iD) throws NotFoundException {
        SQL_SELECT = "SELECT * " +
                "FROM notifications n " +
                "WHERE n.notification_id = ? ";

        List<NotificationData> list = jdbcTemplate.query(SQL_SELECT, this::mapNotificationData, iD);

        if(list.size()==0){
            throw new NotFoundException();
        }

        return list.get(0);
    }

    public int getLastNotificationId()
    {
        SQL_SELECT = "SELECT MAX(notification_id)" +
                "FROM notifications";

        return jdbcTemplate.queryForObject(SQL_SELECT, Integer.class);
    }

    public int setNotificationData(NotificationData data)
    {
        SQL_INSERT =
                "INSERT INTO notifications (user_id, status, type, event_id, sender_id) VALUES (?, ? ,?, ?, ?)";

        jdbcTemplate.update(SQL_INSERT,
                data.getUserId(),
                data.getStatus(),
                data.getType(),
                data.getEventId(),
                data.getSender().getUser_id());

        return getLastNotificationId();
    }

    public List<NotificationData> getUserNotification(int userId)
    {
        SQL_SELECT =
                "SELECT * " +
                        "FROM notifications n " +
                        "WHERE n.user_id = ? ";

        List<NotificationData> list = jdbcTemplate.query(SQL_SELECT, this::mapNotificationData,
                userId);

        return list;
    }

    public void updateNotification(NotificationData notificationData)
    {
        SQL_UPDATE = "UPDATE notifications " +
                "SET " +
                "status = ? " +
                "WHERE notification_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                notificationData.getStatus(),
                notificationData.getNotification_id());
    }

    private NotificationData mapNotificationData(ResultSet rs, int row)
            throws SQLException {

        UserProfileData sender = controlUser.getUserProfileData(rs.getInt("sender_id"));

        return new NotificationData(
                rs.getInt("notification_id"),
                rs.getInt("user_id"),
                rs.getString("status"),
                rs.getString("type"),
                rs.getInt("event_id"),
                sender
        );
    }
}
