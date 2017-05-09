package com.walker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

/**
 * Created by Rafal on 06.05.2017.
 */

@Configuration
@EnableWebSecurity
@Import(DataBaseConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private DataSource dataSource;

    /*@Override protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/Login")
                    .defaultSuccessUrl("/Main")
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .logoutUrl("/Logout")
                .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                .antMatchers("/Profile").authenticated()
                .antMatchers("/Main").authenticated()
                .antMatchers("/Friends").authenticated()
                .antMatchers("/Meeting").authenticated()
                .antMatchers("/Announce").authenticated()
                .antMatchers("/Profile-edit").authenticated()
                .antMatchers("/AnnounceTest").authenticated()
                .anyRequest().permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select nick, password, true " +
                                "from user where nick=?")
                .authoritiesByUsernameQuery(
                        "select nick, 'ROLE_USER' from user where nick=?");
    }

}
