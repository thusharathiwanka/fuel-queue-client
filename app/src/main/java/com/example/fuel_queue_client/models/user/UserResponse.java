package com.example.fuel_queue_client.models.user;

public class UserResponse {
    public UserResponse(String id, String username, String password, String role, String vehicleType) {
        Id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.vehicleType = vehicleType;
    }

    private String Id;
    private String username;
    private String password;
    private String role;
    private String vehicleType;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
