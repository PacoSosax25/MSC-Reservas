package com.ms.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan(basePackages = {"com.ms.hotel.model"})
@EnableJpaRepositories(basePackages = {"com.ms.hotel.dao"})
@ComponentScan(basePackages = {"com.ms.hotel.controllers","com.ms.hotel.dao","com.ms.hotel.service"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
