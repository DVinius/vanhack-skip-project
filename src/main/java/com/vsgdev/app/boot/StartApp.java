package com.vsgdev.app.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.vsgdev"})
@EnableJpaRepositories(basePackages="com.vsgdev.app.repositories")
@EntityScan("com.vsgdev.models")
public class StartApp {
	
	public static void main(String[] args) {
		SpringApplication.run(StartApp.class, args);
	}
	
	/*
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

}
