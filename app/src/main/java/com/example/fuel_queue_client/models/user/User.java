package com.example.fuel_queue_client.models.user;

public class User {
    private int id;
    private String userId;
    private String username;
    private String role;

    public User(int id, String userId, String username, String role) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
