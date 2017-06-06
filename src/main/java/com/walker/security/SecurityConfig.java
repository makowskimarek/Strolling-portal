package com.walker.security;

import com.walker.config.DataBaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

    @Autowired
    private AuthFailure authFailure;
    @Autowired
    private AuthSuccess authSuccess;

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Bean
    public AuthenticationManager myAuthenticationManager()
    {
        try {
            return super.authenticationManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .exceptionHandling()
                    .authenticationEntryPoint(entryPointUnauthorizedHandler)
                .and()
                .formLogin()
                    .successHandler(authSuccess)
                    .failureHandler(authFailure)
                    //.defaultSuccessUrl("/Main")
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
                //.antMatchers("/user").authenticated()
                //.antMatchers("/user/*").authenticated()
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
