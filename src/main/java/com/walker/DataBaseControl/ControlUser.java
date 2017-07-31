package com.walker.DataBaseControl;

import com.walker.DataBase.CriteriaData;
import com.walker.DataBaseControl.databaseException.NotFoundException;
import com.walker.core.entities.*;
import com.walker.model.Model;
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
                "INSERT INTO user_data (user_id, firstName, lastName, city, birth_date) VALUES (?, ?, ?, ? ,?)";
        jdbcTemplate.update(SQL_INSERT,
                userData.getUserId(),
                userData.getFirstName(),
                userData.getLastName(),
                userData.getCity(),
                userData.getDate());
    }

    /**
     * Method to update user data
     *
     * @param IdUser   id of user
     * @param userData information of user
     */
    public void updateUserData(int IdUser, UserData userData) {
        SQL_UPDATE = "UPDATE user_data " +
                "SET firstName = ?, lastName = ?, city = ? , birth_date = ?" +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                userData.getFirstName(),
                userData.getLastName(),
                userData.getCity(),
                userData.getDate(),
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

    public void updateUserMail(int IdUser, String mail) {
        SQL_UPDATE = "UPDATE user " +
                "SET mail = ?" +
                "WHERE user_id = ?";

        jdbcTemplate.update(SQL_UPDATE,
                mail,
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

    public User getUser(int userId) {
        SQL_SELECT =
                "SELECT * " +
                        "FROM user " +
                        "WHERE user_id = ? ";

        List<User> listUser = jdbcTemplate.query(SQL_SELECT, this::mapUser, userId);

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

    public boolean checktUserExsist(int idUser)
    {
        SQL_SELECT =
                "SELECT * " +
                        "FROM user_data " +
                        "WHERE user_id = ? ";

        List<UserData> listUser = jdbcTemplate.query(SQL_SELECT, this::mapUserData,
                idUser);

        if (listUser.size() == 0)
            return false;
        else
            return true;
    }


    public List<UserData> getUsersData(int[] users) {
        SQL_SELECT =
                "SELECT * " +
                        "FROM user_data " +
                        "WHERE user_id IN (?,?,?,?,?,?,?,?,?,?)";

        List<UserData> usersList = jdbcTemplate.query(SQL_SELECT, this::mapUserData,
                users[0], users[1], users[2], users[3], users[4], users[5], users[6], users[7], users[8], users[9]);
        return usersList;
    }

    public UserProfileData getUserProfileData(int userId) {
        SQL_SELECT =
                "SELECT u.user_id, u.nick, u.mail, " +
                        "  ud.firstName, ud.lastName, ud.city, ud.birth_date, " +
                        "  l.latitude, l.longtitude, " +
                        "  up.description, " +
                        "  p.photo_url " +
                        "FROM user u " +
                        "LEFT JOIN user_data ud " +
                        "ON  u.user_id = ud.user_id " +
                        "LEFT JOIN user_profile up " +
                        "ON u.user_id = up.user_id " +
                        "LEFT JOIN location l " +
                        "ON  l.location_id = up.location_id " +
                        "LEFT JOIN photos p " +
                        "ON up.photo_id = p.photo_id " +
                        "WHERE u.user_id = ?";

        List<UserProfileData> user = jdbcTemplate.query(SQL_SELECT, this::mapUserProfile, userId);

        if (user.size()==0) return null;

        return user.get(0);
    }





    public List<UserRange> getUsersByCriteries(double latitude, double longtitude, double range, int ageFrom, int ageTo) {
        Model model = new Model();

        SQL_SELECT =
                "SELECT ud.user_id, ud.birth_date, " +
                        "l.latitude, l.longtitude " +
                        "FROM user_data ud " +
                        "LEFT JOIN user_profile up " +
                        "ON ud.user_id = up.user_id " +
                        "LEFT JOIN location l " +
                        "ON  l.location_id = up.location_id " +
                        "WHERE YEAR(ud.birth_date)< ? AND YEAR(ud.birth_date) > ?";

        List<CriteriaData> locationList = jdbcTemplate.query(SQL_SELECT, this::mapCriteria, 2017 - ageFrom, 2017 - ageTo);
        return model.getNearbyUsers(locationList, latitude, longtitude, range);

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
                rs.getString("city"),
                rs.getString("birth_date")
        );
    }

    private CriteriaData mapCriteria(ResultSet rs, int row)
            throws SQLException {
        return new CriteriaData(
                rs.getInt("user_id"),
                rs.getString("birth_date"),
                rs.getDouble("latitude"),
                rs.getDouble("longtitude")
        );
    }

    private UserProfileData mapUserProfile(ResultSet rs, int row)
            throws SQLException {
        return new UserProfileData(
                rs.getInt("user_id"),
                rs.getString("nick"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("city"),
                rs.getString("birth_date"),
                rs.getDouble("latitude"),
                rs.getDouble("longtitude"),
                rs.getString("description"),
                rs.getString("photo_url"),
                rs.getString("mail")
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
