package com.myExcersise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.example.Configuration.*;

public class Main3 {

    public static void main(String[] args) throws SQLException {

        List<String> name = List.of("Ania","Zosia","Antek","Maks","Wojtek");
        List<Integer> age = List.of(5,3,4,3,5);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO kids VALUES (?,?,?)");

        for (int i = 1; i <=name.size() ; i++) {
            preparedStatement.setInt(1,3+i);
            preparedStatement.setString(2,name.get(i-1));
            preparedStatement.setInt(3,age.get(i-1));
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
        connection.close();
    }
}
