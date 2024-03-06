package com.users.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {

		// ADD DATA TO POSTGRES

		// AddRow insert = new AddRow();
		// insert.Insert_Data();

		// ADD MESSAGE FROM POSTGRES TO TOPIC

		// AddDataPostgres insert = new AddDataPostgres();
		// insert.Insert_Data();

		// ADD MESSAGE TO THE TOPIC

		AddJsonData insert = new AddJsonData();
		insert.produceJsonData();

	}

}
