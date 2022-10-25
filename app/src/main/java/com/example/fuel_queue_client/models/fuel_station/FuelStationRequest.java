package com.example.fuel_queue_client.models.fuel_station;

public class FuelStationRequest {

    private String registrationNumber;
    private String name;
    private String location;
    private String noPumps;
    private String availability;
    private String arrivalTime;
    private String finishTime;

    public FuelStationRequest(String registrationNumber, String name, String location, String noPumps, String availability, String arrivalTime, String finishTime) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.location = location;
        this.noPumps = noPumps;
        this.availability = availability;
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNoPumps() {
        return noPumps;
    }

    public void setNoPumps(String noPumps) {
        this.noPumps = noPumps;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
