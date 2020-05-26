package com.example;

import java.sql.*;

import static com.example.Configuration.*;

public class Main7Injection {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println(login("Jan", "wrongPassword", connection));
        System.out.println(login("Jan", "password1", connection));
        System.out.println(login("Jan", "' OR '1'='1", connection));


        System.out.println(securedLogin("Jan", "' OR '1'='1", connection));
        System.out.println(securedLogin("Jan", "password1", connection));

        connection.close();
    }

    private static boolean securedLogin(final String name, final String password, final Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isLogged= resultSet.next();
        preparedStatement.close();
        return isLogged;
    }

    private static boolean login(final String name, final String password, final Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM user WHERE username='" + name + "'AND password='" + password + "'");
        boolean isLogged = resultSet1.next();
        statement.close();
        return isLogged;

    }
}
