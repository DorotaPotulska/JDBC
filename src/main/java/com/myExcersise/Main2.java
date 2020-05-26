package com.myExcersise;

import java.sql.*;

import static com.example.Configuration.*;

public class Main2 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS kids(id BIGINT NOT NULL, name VARCHAR(50), age INT, PRIMARY KEY(id))");

        statement.executeUpdate("INSERT INTO kids VALUES (1,'Jasio',4),(2,'Marysia',5),(3,'Zosia',3)");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM kids");

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            System.out.println(String.format("id %s ma na imie %s i ma %s lat",id,name,age));
        }
        statement.close();
        connection.close();
    }
}
