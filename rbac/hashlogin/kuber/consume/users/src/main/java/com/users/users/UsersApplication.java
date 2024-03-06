package com.users.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {

		// CONSUME DATA FROM THE TOPIC

		ConsumeJsonData read = new ConsumeJsonData();
		read.ConsumeData();

		// ConsumeData read = new ConsumeData();
		// read.ConsumeData();

	}

}
