package com.example;

import com.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.Configuration.*;

public class Main8UserExercise {
    public static void main(String[] args) throws SQLException {

        List<User> usersList = new ArrayList<>();

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

        while (resultSet.next()){
            long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            User user = new User(id,username,password);
            usersList.add(user);
        }


        statement.close();
        connection.close();

        usersList.forEach(System.out::println);
    }
}
