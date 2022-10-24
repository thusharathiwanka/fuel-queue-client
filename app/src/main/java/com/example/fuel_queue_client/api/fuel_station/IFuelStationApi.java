package com.example.fuel_queue_client.api.fuel_station;

import com.example.fuel_queue_client.models.fuel_station.FuelStationRequest;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IFuelStationApi {

    @POST("/api/FuelStation")
    Call<FuelStationResponse> registerStation(@Body FuelStationRequest request);

}
