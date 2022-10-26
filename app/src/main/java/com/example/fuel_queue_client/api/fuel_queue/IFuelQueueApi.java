package com.example.fuel_queue_client.api.fuel_queue;

import com.example.fuel_queue_client.models.fuel_queue.FuelQueueRequest;
import com.example.fuel_queue_client.models.fuel_queue.FuelQueueResponse;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IFuelQueueApi {

    @POST("/api/FuelQueue")
    Call<FuelQueueResponse> createFuelQueue(@Body FuelQueueRequest request);

    @POST("/api/FuelQueue/join/{id}")
    Call<Boolean> AddUserFuelQueue(@Path("id") String FuelStationID, @Body FuelQueueRequest request);




}
