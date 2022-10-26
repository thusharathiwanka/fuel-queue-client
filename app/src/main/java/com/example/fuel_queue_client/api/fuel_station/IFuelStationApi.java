package com.example.fuel_queue_client.api.fuel_station;

import com.example.fuel_queue_client.models.fuel_station.FuelStationRequest;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IFuelStationApi {

    @POST("/api/FuelStation")
    Call<FuelStationResponse> registerStation(@Body FuelStationRequest request);

    @GET("/api/FuelStation")
    Call<List<FuelStationResponse>> GetAllStations();

    @GET("/api/FuelStation/{id}")
    Call<FuelStationResponse> GetStationByID(@Path("id") String stationID);

    @PUT("/api/FuelStation/{id}")
    Call<FuelStationResponse> UpdateStationByID(@Path("id") String stationID ,@Body FuelStationResponse response);

}
