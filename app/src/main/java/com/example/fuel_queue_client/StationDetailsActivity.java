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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.fuel_station.IFuelStationApi;
import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.fuel_station.FuelStationRequest;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;
import com.example.fuel_queue_client.models.user.User;

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
    String id,availability;
    ImageView backBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_station_details);

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
        backBtn = findViewById(R.id.back3);



        //create fuel station api object
        IFuelStationApi fuelStationApi = APIConfig.getConfig().create(IFuelStationApi.class);
        //retrieves station by id
        Call<FuelStationResponse> call = fuelStationApi.GetStationByID(id);

        //direct to the home page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FuelStationListActivity.class);
                startActivity(intent);
            }
        });

        /***
         Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response
         ***/
        call.enqueue(new Callback<FuelStationResponse>() {
            @Override
            public void onResponse(Call<FuelStationResponse> call, Response<FuelStationResponse> response) {

                //displays a error message, if the response is not successful
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                //save response if the response is successful
                FuelStationResponse fuelStationResponse = response.body();
                //assign values to text field using response
                RegNumber.setText(fuelStationResponse.getRegistrationNumber());
                Station_name.setText(fuelStationResponse.getName());
                Location.setText(fuelStationResponse.getLocation());
                NoPumps.setText(fuelStationResponse.getNoPumps());
            }

            // if request get failed a error message will display
            @Override
            public void onFailure(Call<FuelStationResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // saves value of the switch
        Availability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        availability = "Available";
                    }
                    else{
                        availability = "Unavailable";
                    }
            }
        });


        // update the station object with user entered values
        Update.setOnClickListener(view -> {

            DBHelper dbHelper = new DBHelper(StationDetailsActivity.this);
            User user = dbHelper.getSingleUser();
            //saves all user entered values and existing values inside a FuelStationResponse object
            FuelStationResponse  fuelStationResponse = new FuelStationResponse(id,RegNumber.getText().toString(),user.getUserId(),Station_name.getText().toString(),Location.getText().toString(),NoPumps.getText().toString(),availability,arrivalTime.getText().toString(),finishTime.getText().toString());
            //send request to update fuel station details
            Call<FuelStationResponse> call_Update = fuelStationApi.UpdateStationByID(id,fuelStationResponse);

            /***
             Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response
             ***/
            call_Update.enqueue(new Callback<FuelStationResponse>() {
                @Override
                public void onResponse(Call<FuelStationResponse> call, Response<FuelStationResponse> response) {
                    //display a error message if response unsuccessful
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //directs to user created list of fuel station interface
                    Intent intent = new Intent(StationDetailsActivity.this, FuelStationListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Successfully Updated Station", Toast.LENGTH_SHORT).show();
                    finish();
                }

                //displays toast message,if response of the request is a failure
                @Override
                public void onFailure(Call<FuelStationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
}