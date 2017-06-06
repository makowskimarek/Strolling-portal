package com.walker.config;

import com.walker.core.services.UserService;
import com.walker.core.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Marek Makowski
 * @version 1.0
 */
@Configuration
public class ContrConfig {

    @Bean
    public UserService userServiceImpl(){
        return new UserServiceImpl();
    }
}