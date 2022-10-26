package com.example.fuel_queue_client.models.fuel_queue;

public class QueueCustomer {

    private String userId;
    private Boolean status;
    private String detailedStatus;
    private String vehicleType;
    private String enteredTime;
    private String exitedTime;

    public QueueCustomer(String userId, Boolean status, String detailedStatus, String vehicleType, String enteredTime, String exitedTime) {
        this.userId = userId;
        this.status = status;
        this.detailedStatus = detailedStatus;
        this.vehicleType = vehicleType;
        this.enteredTime = enteredTime;
        this.exitedTime = exitedTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDetailedStatus() {
        return detailedStatus;
    }

    public void setDetailedStatus(String detailedStatus) {
        this.detailedStatus = detailedStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEnteredTime() {
        return enteredTime;
    }

    public void setEnteredTime(String enteredTime) {
        this.enteredTime = enteredTime;
    }

    public String getExitedTime() {
        return exitedTime;
    }

    public void setExitedTime(String exitedTime) {
        this.exitedTime = exitedTime;
    }
}
