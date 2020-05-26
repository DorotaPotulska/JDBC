package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.example.Configuration.*;

public class Main6InsertPreparedStatement {
    public static void main(String[] args) throws SQLException {

        List<String> names = List.of("Jan", "Ala", "Mikołaj", "Ola");
        List<String> password = List.of("password1", "password2", "password3", "password4");

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?,?,?)");

        for (int i = 1; i <= names.size(); i++) {
            preparedStatement.setLong(1, i);
            preparedStatement.setString(2, names.get(i - 1));
            preparedStatement.setString(3, password.get(i - 1));
            preparedStatement.executeUpdate();
        }


        preparedStatement.close();
        connection.close();
    }
}
