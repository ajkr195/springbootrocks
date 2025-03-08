package com.spring.boot.rocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println(
				"\nApplication URL is: http://localhost:8080\nUsername is: admin@admin\nPassword is: admin@admin");
	}

}
