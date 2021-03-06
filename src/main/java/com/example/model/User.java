package com.example.model;

public class User {

    private Long id;
    private String username;
    private String password;

    public User(final Long id, final String username, final String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
