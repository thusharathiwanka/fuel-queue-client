package com.example.fuel_queue_client.api.user;

import com.example.fuel_queue_client.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface IUserApi {
    /***
     * Add a new user to the fuel Queue
     * @param UserId = Id of the User
     * @return = a boolean value
     */
    @DELETE("/api/User/{id}")
    Call<UserResponse> DeleteUser(@Path("id") String UserId);
}
