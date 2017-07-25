package com.walker.config;

import com.walker.core.entities.Id;
import com.walker.security.AuthFailure;
import com.walker.security.AuthSuccess;
import com.walker.security.CustomLogoutHandler;
import com.walker.security.EntryPointUnauthorizedHandler;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Rafal on 24.04.2017.
 */

@Configuration
public class DataBaseConfig {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("1q2w3e4r");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        return new JdbcTemplate(dataSource());
    }

    @Bean
    public AuthFailure authFailure()
    {
        return new AuthFailure();
    }

    @Bean
    public AuthSuccess authSuccess()
    {
        return new AuthSuccess();
    }

    @Bean
    public CustomLogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler();
    }

    @Bean
    public EntryPointUnauthorizedHandler entryPointUnauthorizedHandler() {
        return new EntryPointUnauthorizedHandler();
    }

    @Bean
    public Id id() {return new Id();}
}
