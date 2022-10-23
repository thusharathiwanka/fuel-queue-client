package com.example.fuel_queue_client.api.auth;

import com.example.fuel_queue_client.models.user.UserRequest;
import com.example.fuel_queue_client.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthApi {
    @POST("/api/User")
    Call<UserResponse> registerUser(@Body UserRequest user);

    @POST("/api/User/login")
    Call<UserResponse> loginUser(@Body UserRequest user);
}
