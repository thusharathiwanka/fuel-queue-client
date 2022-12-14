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
import com.example.fuel_queue_client.api.fuel_queue.IFuelQueueApi;
import com.example.fuel_queue_client.api.fuel_station.IFuelStationApi;
import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.fuel_queue.FuelQueueRequest;
import com.example.fuel_queue_client.models.fuel_queue.FuelQueueResponse;
import com.example.fuel_queue_client.models.fuel_queue.QueueCustomer;
import com.example.fuel_queue_client.models.fuel_station.FuelStationRequest;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;
import com.example.fuel_queue_client.models.user.User;

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
            DBHelper dbHelper = new DBHelper(RegisterStationActivity.this);
            User user = dbHelper.getSingleUser();
            //saves all user entered values and existing values inside a FuelStationResponse object
            IFuelStationApi fuelStationApi = APIConfig.getConfig().create(IFuelStationApi.class);
            //make request to save a fuel station object in database
            Call<FuelStationResponse> call = fuelStationApi.registerStation(new FuelStationRequest( registrationNumber,user.getUserId(),name,location,noPumps,availability,arrivalTime,finishTime));






            /***
             Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response
             ***/
            call.enqueue(new Callback<FuelStationResponse>() {
                @Override
                public void onResponse(Call<FuelStationResponse> call, Response<FuelStationResponse> response) {
                    //display a error message if response unsuccessful
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    FuelStationResponse fuelStationResponse = response.body();
                    IFuelQueueApi fuelQueueApi = APIConfig.getConfig().create(IFuelQueueApi.class);
                    DBHelper dbHelper = new DBHelper(RegisterStationActivity.this);
                    User user = dbHelper.getSingleUser();
                    FuelQueueRequest fuelQueueRequest = new FuelQueueRequest(fuelStationResponse.getId(), 0,null);
                    Call<FuelQueueResponse> call_Queue = fuelQueueApi.createFuelQueue(fuelQueueRequest);

                    call_Queue.enqueue(new Callback<FuelQueueResponse>() {
                        @Override
                        public void onResponse(Call<FuelQueueResponse> call, Response<FuelQueueResponse> response) {
                            //display a error message if response unsuccessful
                            if (!response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                return;
                            }
                              Toast.makeText(getApplicationContext(), "Station & Queue Successfully Created", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<FuelQueueResponse> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    });

                    //directs to fuel station list activity
                    Intent intent = new Intent(RegisterStationActivity.this, FuelStationListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                    finish();
                }

                //displays toast message,if response of the request is a failure
                @Override
                public void onFailure(Call<FuelStationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }


            });

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterStationActivity.this, OwnerProfileActivity.class);
                startActivity(intent);
            }
        });

    }


}