package com.walker.DataBaseControl;

import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.ChatData;
import com.walker.core.entities.UserMessageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Marek Makowski
 * @version 1.0
 */
@Repository
public class ControlUserMessage {

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


    public ControlUserMessage() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);
    }

    /**
     * Method to add user message to database
     *
     * @param userMessageData information of message
     */
    public void addUserMessage(UserMessageData userMessageData) {
        SQL_INSERT =
                "INSERT INTO messages (sender_id, receiver_id, status, msg_time, message) VALUES (?, ?, ?, ? ,?)";
        jdbcTemplate.update(SQL_INSERT,
                userMessageData.getSenderId(),
                userMessageData.getReceiverId(),
                userMessageData.getStatus(),
                userMessageData.getMsgTime(),
                userMessageData.getMessage());
    }

    /**
     * Method to get user by nick
     *
     * @param userId id of user
     * @return user
     */
    public List<UserMessageData> getAllUserMessages(int userId) throws NotFoundException {
        SQL_SELECT =
                "SELECT * " +
                        "FROM messages " +
                        "WHERE sender_id = ?  OR receiver_id = ?";

        List<UserMessageData> userMessageDataList = jdbcTemplate.query(SQL_SELECT, this::mapUserMessages, userId, userId);

        if (userMessageDataList.size() == 0)
            throw new NotFoundException();
        else {
            readMessages(userMessageDataList);
            return userMessageDataList;
        }
    }

    /**
     * Method to get user by nick
     *
     * @param userId id of user
     * @return user
     */
    public List<UserMessageData> getUserMessages(int userId, int userId2) throws NotFoundException {
        SQL_SELECT =
                "SELECT * " +
                        "FROM messages " +
                        "WHERE (sender_id = ? AND receiver_id = ?) " +
                        "OR (sender_id = ? AND receiver_id = ?)";

        List<UserMessageData> userMessageDataList = jdbcTemplate.query(SQL_SELECT, this::mapUserMessages, userId, userId2, userId2, userId);

        if (userMessageDataList.size() == 0)
            throw new NotFoundException();
        else {
            readMessages(userMessageDataList);
            return userMessageDataList;
        }
    }

    private void readMessages(List<UserMessageData> messages) {
        for (UserMessageData message : messages) {
            SQL_UPDATE = "UPDATE messages " +
                    "SET status = ?" +
                    "WHERE msg_id = ?";

            jdbcTemplate.update(SQL_UPDATE,
                    "read",
                    message.getMsgId());
        }
    }


    public List<ChatData> getRecentChatList(int userId) throws NotFoundException {
        SQL_SELECT = "SELECT udata.user_id, udata.firstName, udata.lastName, pho.photo_url FROM user_data udata " +
                "INNER JOIN user_profile upro " +
                "ON udata.user_id = upro.user_id " +
                "INNER JOIN photos pho " +
                "ON upro.photo_id = pho.photo_id " +
                "WHERE udata.user_id = ?";
        List<ChatData> chatDataList = jdbcTemplate.query(SQL_SELECT, this::mapChatData, userId);
        if (chatDataList.size() == 0)
            throw new NotFoundException();
        else {
            return chatDataList;
        }
    }


    /**
     * Method to extract value from object resultSet and creating from then UserMessageData
     *
     * @param rs
     * @param row
     * @return
     * @throws SQLException
     */
    private UserMessageData mapUserMessages(ResultSet rs, int row)
            throws SQLException {
        return new UserMessageData(
                rs.getInt("msg_id"),
                rs.getInt("sender_id"),
                rs.getInt("receiver_id"),
                rs.getString("status"),
                rs.getString("msg_time"),
                rs.getString("message")
        );
    }

    /**
     * Method to extract value from object resultSet and creating from then ChatData
     *
     * @param rs
     * @param row
     * @return
     * @throws SQLException
     */
    private ChatData mapChatData(ResultSet rs, int row)
            throws SQLException {
        return new ChatData(
                rs.getInt("user_id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getBytes("photo_url")
        );
    }
}