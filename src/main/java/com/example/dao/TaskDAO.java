package com.example.dao;

import com.example.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.Configuration.*;

public class TaskDAO implements AutoCloseable{
    private Connection connection = null;

    public TaskDAO() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS task(id BIGINT NOT NULL, description VARCHAR(255), user_id BIGINT, PRIMARY KEY (id), CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user(id))");
            statement.executeUpdate("DELETE from task");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Task task) throws SQLException {
        // tworzymy nowy task w bazie danych na podstawie informacji z argumentu


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task VALUE(?,?,?)");
            preparedStatement.setLong(1,task.getId());
            preparedStatement.setString(2,task.getDescription());
            preparedStatement.setLong(3,task.getUserId());
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Optional<Task> read(long id) throws SQLException {
        // wyciągamy dane z bazy na podstawie id taska i przypisujemy do obiektu klasy Task
        // jeśli znajdzie wiersz to zwracamy Optional.of(new Task(...))
        // jeśli nie znajdzie to Optional.empty()

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE id=?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            long taskId = resultSet.getLong("id");
            String description = resultSet.getString("description");
            long userId = resultSet.getLong("user_id");
            preparedStatement.close();
            return Optional.of(new Task(taskId,description,userId));
        }

        preparedStatement.close();

        return Optional.empty();
    }

    public List<Task> readAll() throws SQLException {
        // wyciągamy wszystkie wiersze z bazy danych
        // wyniki zapisujemy w liście obiektów klasy Task

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM task");

        List<Task>taskList = new ArrayList<>();


            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String description = resultSet.getString(2);
                long userId = resultSet.getLong(3);
                taskList.add(new Task(id, description, userId));
            }
            statement.close();
            return taskList;

    }

    public void update(Task task) throws SQLException {
        // aktualizujemy description i user_id na podstawie id taska
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task SET description=?, user_id=? WHERE id=?");
        preparedStatement.setString(1,task.getDescription());
        preparedStatement.setLong(2,task.getUserId());
        preparedStatement.setLong(3,task.getId());


        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(long id) throws SQLException {
        // usuwamy wiersz z bazy na podstawie id taska

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM task WHERE id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Task> readAllForUser(String username) throws SQLException {
        // dla ochotników
        // konstruujemy query z użyciem JOIN i odwołaniem do tabeli user
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE user_id in (SELECT id FROM user WHERE username=?) ");
        preparedStatement.setString(1,username);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Task>taskList = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                long userId = resultSet.getLong("user_id");

                taskList.add(new Task(id, description, userId));
            }
        preparedStatement.close();
            return taskList;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
