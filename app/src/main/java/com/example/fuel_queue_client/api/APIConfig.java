package com.example.fuel_queue_client.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfig {
    public static final String BASE_URL = "https://f998-123-231-124-148.in.ngrok.io";
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
