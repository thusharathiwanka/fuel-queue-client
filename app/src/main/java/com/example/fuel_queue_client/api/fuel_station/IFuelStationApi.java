package com.example.fuel_queue_client.api.fuel_station;

import com.example.fuel_queue_client.models.fuel_queue.FuelQueueResponse;
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

    /***
     * save a fuel station inside MongoDb
     * @param request  =  FuelStationRequest object
     * @return = FuelStationResponse
     */
    @POST("/api/FuelStation")
    Call<FuelStationResponse> registerStation(@Body FuelStationRequest request);

    /***
     * retrieve all fuel station inside MongoDb
     * @return =FuelStationResponse list
     */
    @GET("/api/FuelStation")
    Call<List<FuelStationResponse>> GetAllStations();

    /***
     *  retrieve a fuel station using station Id
     * @param stationID = ID of the fuel station
     * @return = FuelStationResponse object
     */
    @GET("/api/FuelStation/{id}")
    Call<FuelStationResponse> GetStationByID(@Path("id") String stationID);

    /***
     * update fuel station using id and FuelStationResponse object
     * @param stationID = ID of the fuel station
     * @param response = FuelStationResponse object
     * @return  = updated version of FuelStationResponse
     */
    @PUT("/api/FuelStation/{id}")
    Call<FuelStationResponse> UpdateStationByID(@Path("id") String stationID ,@Body FuelStationResponse response);


    /***
     *
     * @param userId - owner id
     * @return  fuel station list
     */

    @GET("api/fuel-station/owner/{id}")
    Call<FuelStationResponse> GetFuelStationByUserID(@Path("id") String userId);

}
