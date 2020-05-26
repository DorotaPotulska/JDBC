package com.example;


import java.sql.*;

import static com.example.Configuration.*;

public class Main3Statement {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        boolean execute = statement.execute("SELECT * FROM animal");
        System.out.println(execute);

        int count = statement.executeUpdate("UPDATE animal SET name='Jasio' WHERE id=2");
        System.out.println(count);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM animal");
        while (resultSet.next()){
            String name = resultSet.getString("name");
            System.out.println(name);
        };
        statement.close();
        connection.close();
    }
}
