package com.example.fuel_queue_client.api.auth;

import com.example.fuel_queue_client.models.user.UserRequest;
import com.example.fuel_queue_client.models.user.UserResponse;

import retrofit2.Call;

public class AuthApi implements IAuthApi {
    @Override
    public Call<UserResponse> registerUser(UserRequest user) {
        return null;
    }

    @Override
    public Call<UserResponse> loginUser(UserRequest user) {
        return null;
    }
}
