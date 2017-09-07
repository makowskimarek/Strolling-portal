package com.walker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.dailyadvisor.repositories")
@EntityScan(basePackages = "com.walker")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	} 
}