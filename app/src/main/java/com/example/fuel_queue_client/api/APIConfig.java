package com.example.fuel_queue_client.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfig {
    public static final String BASE_URL = "https://localhost:7081";
    public static Retrofit retrofit = null;

    public static Retrofit getConfig() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
