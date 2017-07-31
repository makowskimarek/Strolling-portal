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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    private CustomLogoutHandler customLogoutHandler;

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
                .and()
                .logout()
                    .logoutSuccessHandler(customLogoutHandler)
                    .logoutUrl("/logout")
                .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                .antMatchers("/user/*").authenticated()
                .antMatchers("/profile/*").authenticated()
                .antMatchers("/adv/*").authenticated()
                .antMatchers("/friends/*").authenticated()
                .antMatchers("/notification/*").authenticated()
                .antMatchers("/search/*").authenticated()
                .antMatchers("/stroll/*").authenticated()
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
