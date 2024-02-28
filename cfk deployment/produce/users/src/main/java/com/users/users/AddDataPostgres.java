package com.users.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;

public class AddDataPostgres {

    GenerateData generateData = new GenerateData();

    private final String dburl = "jdbc:postgresql://localhost:5432/users";
    private final String username = "postgres";
    private final String password = "postgres";

    public void Insert_Data() {

        // JDBC variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(dburl, username, password);

            String sql = "INSERT INTO USERDATA (fname, lname, address, email, contact) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, generateData.GetFname());
            preparedStatement.setString(2, generateData.GetLname());
            preparedStatement.setString(3, generateData.GetAddress());
            preparedStatement.setString(4, generateData.GetEmail());
            preparedStatement.setString(5, generateData.GetContact());

            int noRows = preparedStatement.executeUpdate();
            System.out.println(noRows + " row(s) inserted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
