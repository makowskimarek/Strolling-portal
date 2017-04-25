package com.walker.DataBaseControl;

import com.walker.DataBase.User;
import com.walker.DataBase.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
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
public class JdbcWalkerRepository {

    private String SQL_INSERT;
    private String SQL_SELECT;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*@Autowired
    public JdbcWalkerRepository(JdbcOperations jdbcOperations) {
        this.jdbcTemplate = jdbcOperations;
    }*/
    public JdbcWalkerRepository() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");

        jdbcTemplate =  new JdbcTemplate(ds);
    };

    public void addUserData(UserData userData)
    {
        SQL_INSERT =
        "insert into user_data (user_id, name, surname, city) values (?, ?, ? ,?)";
        jdbcTemplate.update(SQL_INSERT,
                userData.getUserId(),
                userData.getName(),
                userData.getSurname(),
                userData.getCity());
    }

    public void register(UserData userData, User user)
    {
        SQL_INSERT =
                "insert into user (nick, password, mail) values (?, ? ,?)";

        jdbcTemplate.update(SQL_INSERT,
                user.getNick(),
                user.getPassword(),
                user.getMail());

        SQL_SELECT =
                "select * " +
                "from user " +
                "where nick like ? and password like ? and mail like ?";

        List<User> listUser =   jdbcTemplate.query(SQL_SELECT,this::mapUser,
                user.getNick(),
                user.getPassword(),
                user.getMail());


            userData.setId(listUser.get(0).getUser_id());
            addUserData(userData);

    }

    private User mapUser(ResultSet rs, int row)
            throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("nick"),
                rs.getString("password"),
                rs.getString("mail"));
    }
}
