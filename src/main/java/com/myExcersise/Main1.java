package com.myExcersise;

import java.sql.*;

import static com.example.Configuration.*;

public class Main1 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM animal");

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            long age = resultSet.getLong("age");
            System.out.println(String.format("Zwierze o %s id to %s w wieku %s lat",id,name, age));
        }

        int i = statement.executeUpdate("UPDATE animal SET age='66' WHERE name ='Pimpek' ");
        System.out.println(i);


        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM animal");
        while(resultSet1.next()){
            int id = resultSet1.getInt("id");
            String name = resultSet1.getString("name");
            long age = resultSet1.getLong("age");
            System.out.println(String.format("Zwierze o %s id to %s w wieku %s lat",id,name, age));
        }

        statement.close();
        connection.close();


    }
}
