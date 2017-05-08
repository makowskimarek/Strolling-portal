package com.walker.DataBaseControl;

import com.walker.DataBase.User;
import com.walker.DataBase.UserData;
import com.walker.config.DataBaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.annotation.Secured;
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
        ds.setUrl("jdbc:mysql://localhost:3306/db1");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate =  new JdbcTemplate(ds);
    }

    /**
     *  Method to add user data to database
     * @param userData information of user
     * @param IdUser id of user
     */
    public void addUserData(int IdUser, UserData userData)
    {
        SQL_INSERT =
                "insert into user_data (user_id, name, surname, city) values (?, ?, ? ,?)";
        jdbcTemplate.update(SQL_INSERT,
                IdUser,
                userData.getName(),
                userData.getSurname(),
                userData.getCity());
    }

    /**
     * Method to update user data
     * @param IdUser id of user
     * @param userData information of user
     */
    public void updateUserData(int IdUser, UserData userData)
    {
        SQL_UPDATE = "UPDATE user_data" +
                "SET name = ?, surname = ?, city = ?" +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                userData.getName(),
                userData.getSurname(),
                userData.getCity(),
                IdUser);
    }

    /**
     * Method to add user to data base
     * @param user
     */
    public void addUser(User user)
    {
        SQL_INSERT =
                "insert into user (nick, password, mail) values (?, ? ,?)";

        jdbcTemplate.update(SQL_INSERT,
                user.getNick(),
                user.getPassword(),
                user.getMail());

    }

    /**
     * Method to update user
     * @param IdUser user
     * @param user id of user
     */
    public void updateUser(int IdUser, User user)
    {
        SQL_UPDATE = "UPDATE user" +
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
     * @param nick nick of user
     * @return user
     */
    public User getUser(String nick)
    {
        SQL_SELECT =
                "select * " +
                        "from user " +
                        "where nick like ? ";

        List<User> listUser =   jdbcTemplate.query(SQL_SELECT,this::mapUser,
                nick);

        if (listUser.size() == 0)
            return null;
        else
            return listUser.get(0);
    }

    public UserData getUserData(int idUser)
    {
        SQL_SELECT =
                "select * " +
                        "from user_data " +
                        "where user_id = ? ";

        List<UserData> listUser =   jdbcTemplate.query(SQL_SELECT,this::mapUserData,
                idUser);

        if (listUser.size() == 0)
            return null;
        else
            return listUser.get(0);
    }

    /**
     * Method to extract value from object resultSet and creating from then user
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
     * @param rs
     * @param row
     * @return
     * @throws SQLException
     */
    private UserData mapUserData(ResultSet rs, int row)
            throws SQLException {
        return new UserData(
                rs.getInt("user_id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("city")
        );
    }
}
