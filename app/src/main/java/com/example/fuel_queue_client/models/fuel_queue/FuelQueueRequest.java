package com.example.fuel_queue_client.models.fuel_queue;

import com.example.fuel_queue_client.QueueDetails;
import com.example.fuel_queue_client.models.user.UserRequest;

public class FuelQueueRequest {

    private String fuelStationId;
    private int numberOfVehicles;
    private QueueCustomer customers;

    public FuelQueueRequest(String fuelStationId, int numberOfVehicles, QueueCustomer customers) {
        this.fuelStationId = fuelStationId;
        this.numberOfVehicles = numberOfVehicles;
        this.customers = customers;
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
