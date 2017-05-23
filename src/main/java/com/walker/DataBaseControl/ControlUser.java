package com.walker.DataBaseControl;

import com.walker.DataBase.User;
import com.walker.DataBase.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rafal on 24.04.2017.
 */
@Repository
public class ControlUser {

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

    public ControlUser() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate = new JdbcTemplate(ds);
    }

    /**
     * Method to add user data to database
     *
     * @param userData information of user
     */
    public void addUserData(UserData userData) {
        SQL_INSERT =
                "INSERT INTO user_data (user_id, firstName, lastName, city) VALUES (?, ?, ? ,?)";
        jdbcTemplate.update(SQL_INSERT,
                userData.getUserId(),
                userData.getName(),
                userData.getLastName(),
                userData.getCity());
    }

    /**
     * Method to update user data
     *
     * @param IdUser   id of user
     * @param userData information of user
     */
    public void updateUserData(int IdUser, UserData userData) {
        SQL_UPDATE = "UPDATE user_data " +
                "SET firstName = ?, lastName = ?, city = ?" +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                userData.getName(),
                userData.getLastName(),
                userData.getCity(),
                IdUser);
    }

    /**
     * Method to add user to data base
     *
     * @param user
     */
    public boolean addUser(User user) {
        if (getUserID(user.getNick()) != -1) {
            return false;
        } else {
            SQL_INSERT =
                    "INSERT INTO user (nick, password, mail) VALUES (?, ? ,?)";

            jdbcTemplate.update(SQL_INSERT,
                    user.getNick(),
                    user.getPassword(),
                    user.getMail());
            return true;
        }
    }

    /**
     * Method to update user
     *
     * @param IdUser user
     * @param user   id of user
     */
    public void updateUser(int IdUser, User user) {
        SQL_UPDATE = "UPDATE user " +
                "SET nick = ?, password = ?, mail = ?" +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                user.getNick(),
                user.getPassword(),
                user.getMail(),
                IdUser);
    }

    /**
     * Method to get user by nick
     *
     * @param nick nick of user
     * @return user
     */
    public User getUser(String nick) {
        SQL_SELECT =
                "SELECT * " +
                        "FROM user " +
                        "WHERE nick LIKE ? ";

        List<User> listUser = jdbcTemplate.query(SQL_SELECT, this::mapUser,
                nick);

        if (listUser.size() == 0)
            return null;
        else
            return listUser.get(0);
    }

    public int getUserID(String nick) {
        SQL_SELECT =
                "SELECT * " +
                        "FROM user " +
                        "WHERE nick LIKE ? ";

        List<User> listUser = jdbcTemplate.query(SQL_SELECT, this::mapUser,
                nick);

        if (listUser.size() == 0)
            return -1;
        else
            return listUser.get(0).getUser_id();
    }

    public UserData getUserData(int idUser) {
        SQL_SELECT =
                "SELECT * " +
                        "FROM user_data " +
                        "WHERE user_id = ? ";

        List<UserData> listUser = jdbcTemplate.query(SQL_SELECT, this::mapUserData,
                idUser);

        if (listUser.size() == 0)
            return null;
        else
            return listUser.get(0);
    }

    /**
     * Method to extract value from object resultSet and creating from then user
     *
     * @param rs
     * @param row
     * @return
     * @throws SQLException
     */
    private User mapUser(ResultSet rs, int row)
            throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("nick"),
                rs.getString("password"),
                rs.getString("mail"));
    }

    /**
     * Method to extract value from object resultSet and creating from then userData
     *
     * @param rs
     * @param row
     * @return
     * @throws SQLException
     */
    private UserData mapUserData(ResultSet rs, int row)
            throws SQLException {
        return new UserData(
                rs.getInt("user_id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("city")
        );
    }
}
