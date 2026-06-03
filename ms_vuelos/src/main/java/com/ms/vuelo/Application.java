package com.ms.vuelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan(basePackages= {"com.ms.vuelo.model"})
@EnableJpaRepositories(basePackages= {"com.ms.vuelo.dao"})
@ComponentScan(basePackages= {"com.ms.vuelo.controllers","com.ms.vuelo.dao","com.ms.vuelo.service"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

}
