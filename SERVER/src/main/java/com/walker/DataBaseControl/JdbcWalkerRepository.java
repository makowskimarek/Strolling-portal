package com.walker.DataBaseControl;

import com.walker.DataBase.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

/**
 * Created by Rafal on 24.04.2017.
 */
@Repository
public class JdbcWalkerRepository {

    private static final String SQL_INSERT_SPITTER =
            "insert into person (name, surname, date, city) values (?, ?, ?,?)";

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

    public void addPerson(Person person)
    {
        jdbcTemplate.update(SQL_INSERT_SPITTER,
                person.getName(),
                person.getSurname(),
                person.getDate(),
                person.getCity());
    }
}
