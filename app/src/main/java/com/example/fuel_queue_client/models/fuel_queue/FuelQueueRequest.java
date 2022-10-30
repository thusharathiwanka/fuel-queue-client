package com.example.fuel_queue_client.models.fuel_queue;

import com.example.fuel_queue_client.QueueDetails;
import com.example.fuel_queue_client.models.user.UserRequest;
/*
* FuelQueueRequest: class - Represents fuel queue request for the API call
* */
public class FuelQueueRequest {

    private String fuelStationId;
    private int numberOfVehicles;
    private QueueCustomer customer;

    public FuelQueueRequest(String fuelStationId, int numberOfVehicles, QueueCustomer customer) {
        this.fuelStationId = fuelStationId;
        this.numberOfVehicles = numberOfVehicles;
        this.customer = customer;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public QueueCustomer getCustomers() {
        return customer;
    }

    public void setCustomers(QueueCustomer customer) {
        this.customer = customer;
    }
}
