package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.fuel_station.IFuelStationApi;
import com.example.fuel_queue_client.models.fuel_station.FuelStationRequest;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterStationActivity extends AppCompatActivity {

    ImageView backBtn;
    Button regBtn;
    EditText Registration_numberInput, stationNameInput, locationInput,noOfPumps;

    String registrationNumber;
    String name;
    String location;
    String noPumps;
    String availability = "";
    String arrivalTime ="";
    String finishTime ="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register_station);
        backBtn = findViewById(R.id.back);
        regBtn = findViewById(R.id.stationRegisterBtn);
        Registration_numberInput = findViewById(R.id.Registration_numberInput);
        stationNameInput = findViewById(R.id.stationNameInput);
        locationInput = findViewById(R.id.locationInput);
        noOfPumps = findViewById(R.id.noOfPumps);


        regBtn.setOnClickListener(view -> {
            registrationNumber = Registration_numberInput.getText().toString();
            name = stationNameInput.getText().toString();
            location = locationInput.getText().toString();
            noPumps = noOfPumps.getText().toString();


            IFuelStationApi fuelStationApi = APIConfig.getConfig().create(IFuelStationApi.class);
            Call<FuelStationResponse> call = fuelStationApi.registerStation(new FuelStationRequest( registrationNumber,name,location,noPumps,availability,arrivalTime,finishTime));

            call.enqueue(new Callback<FuelStationResponse>() {
                @Override
                public void onResponse(Call<FuelStationResponse> call, Response<FuelStationResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(RegisterStationActivity.this, FuelStationListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<FuelStationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }


            });

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterStationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}