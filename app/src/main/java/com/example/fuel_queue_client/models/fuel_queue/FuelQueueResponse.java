package com.example.fuel_queue_client.models.fuel_queue;

import com.example.fuel_queue_client.models.user.UserRequest;

public class FuelQueueResponse {
    private int id ;
    private String fuelStationId;
    private int numberOfVehicles;
    private QueueCustomer customers;


    public FuelQueueResponse(int id, String fuelStationId, int numberOfVehicles, QueueCustomer customers) {
        this.id = id;
        this.fuelStationId = fuelStationId;
        this.numberOfVehicles = numberOfVehicles;
        this.customers = customers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return customers;
    }

    public void setCustomers(QueueCustomer customers) {
        this.customers = customers;
    }
}
