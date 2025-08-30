package com.project.firstSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

@SpringBootApplication
public class FirstSpringProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(FirstSpringProjectApplication.class, args);

	}

}
