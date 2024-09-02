package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWithSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithSpringDataApplication.class, args);
		System.out.println("SpringBoot up on port number 8585");
	}

}
