package com.example.fuel_queue_client.models.fuel_queue;

import com.example.fuel_queue_client.models.user.UserRequest;

import java.util.List;

/*
 * FuelQueueResponse: class - Represents fuel queue response from the API call
 * */
public class FuelQueueResponse {
    private int id ;
    private String fuelStationId;
    private int numberOfVehicles;
    private List<QueueCustomer> customers;


    public FuelQueueResponse(int id, String fuelStationId, int numberOfVehicles, List<QueueCustomer> customers) {
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

    public List<QueueCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<QueueCustomer> customers) {
        this.customers = customers;
    }
}
