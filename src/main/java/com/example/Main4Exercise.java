package com.example;

import java.sql.*;

import static com.example.Configuration.*;

public class Main4Exercise {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS user(id BIGINT NOT NULL, username VARCHAR(50), password VARCHAR(50), PRIMARY KEY(id))");

        statement.executeUpdate("INSERT INTO user VALUES (1,'Dorota','biały'),(2,'Piotr','niebieski'),(3,'Maks','czerwony'),(4,'Anastazja','zolty'),(5,'Ela','fiolet'),(6,'Mietek','zielen')");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        while (resultSet.next()){
            String name = resultSet.getString("username");
            System.out.println(name);
        };

        statement.executeUpdate("DELETE FROM user");

        statement.close();
        connection.close();
    }

}
