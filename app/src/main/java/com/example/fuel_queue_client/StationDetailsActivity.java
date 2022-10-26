package com.example.fuel_queue_client;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.fuel_station.IFuelStationApi;
import com.example.fuel_queue_client.models.fuel_station.FuelStationRequest;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationDetailsActivity extends AppCompatActivity {

    TextView RegNumber,Station_name,Location,NoPumps;
    EditText arrivalTime, finishTime;
    Switch Availability;
    Button Update;
    String id;
    Boolean availability;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_station_details);

//        name =getIntent().getStringExtra("STATION_NAME");
//        location =getIntent().getStringExtra("STATION_LOCATION");
//        noPumps = getIntent().getStringExtra("STATION_NO_OF_PUMPS");
//        registrationNumber =getIntent().getStringExtra("STATION_REGISTRATION_NUM");

        id =getIntent().getStringExtra("STATION_ID");
        System.out.println("StationID" + id);
        RegNumber = findViewById(R.id.Reg_num);
        Station_name = findViewById(R.id.Station_name);
        Location = findViewById(R.id.Station_Location);
        NoPumps = findViewById(R.id.Num_pumps);
        arrivalTime =findViewById(R.id.ArrivalTime);
        finishTime = findViewById(R.id.finishTime);
        Availability = findViewById(R.id.switch_avl);
        Update = findViewById(R.id.update_station);



        IFuelStationApi fuelStationApi = APIConfig.getConfig().create(IFuelStationApi.class);
        Call<FuelStationResponse> call = fuelStationApi.GetStationByID(id);

        call.enqueue(new Callback<FuelStationResponse>() {
            @Override
            public void onResponse(Call<FuelStationResponse> call, Response<FuelStationResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                FuelStationResponse fuelStationResponse = response.body();
                RegNumber.setText(fuelStationResponse.getRegistrationNumber());
                Station_name.setText(fuelStationResponse.getName());
                Location.setText(fuelStationResponse.getLocation());
                NoPumps.setText(fuelStationResponse.getNoPumps());
            }

            @Override
            public void onFailure(Call<FuelStationResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Availability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    availability = isChecked;
            }
        });


        Update.setOnClickListener(view -> {
            System.out.println(Availability.getText().toString());
            FuelStationResponse  fuelStationResponse = new FuelStationResponse(id,RegNumber.getText().toString(),Station_name.getText().toString(),Location.getText().toString(),NoPumps.getText().toString(),Availability.getText().toString(),arrivalTime.getText().toString(),finishTime.getText().toString());

            Call<FuelStationResponse> call_Update = fuelStationApi.UpdateStationByID(id,fuelStationResponse);
            call_Update.enqueue(new Callback<FuelStationResponse>() {
                @Override
                public void onResponse(Call<FuelStationResponse> call, Response<FuelStationResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(StationDetailsActivity.this, FuelStationListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Successfully Updated Station", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<FuelStationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
}