package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.fuel_queue.IFuelQueueApi;
import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.fuel_queue.FuelQueueRequest;
import com.example.fuel_queue_client.models.fuel_queue.FuelQueueResponse;
import com.example.fuel_queue_client.models.fuel_queue.QueueCustomer;
import com.example.fuel_queue_client.models.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueDetails extends AppCompatActivity {
    ImageView backBtn;
    Button join_Queue,queue_vehicle_type;
    TextView branch_name,availability;
    TextView total_vehicleAmount,vehicle_name,vehicle_amount,departure_time,fuelType,fuelTypeStatus;
    String Station_Id,Station_Name,queue_Availability;
    int Total_vehicles = 0;
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<Integer> subTitle = new ArrayList<Integer>();

    QueueCustomer queueCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_queue_details);
        backBtn = findViewById(R.id.back);
        join_Queue = findViewById(R.id.joinQueue);
        branch_name = findViewById(R.id.branchName);
        availability = findViewById(R.id.queueAvailability);
        total_vehicleAmount = findViewById(R.id.totalVehicleAmount);
//        vehicle_name = findViewById(R.id.vehicleName);
//        vehicle_amount = findViewById(R.id.vehicleAmount);
        departure_time = findViewById(R.id.departureTime);
        queue_vehicle_type = findViewById(R.id.queueVehicleType);


        Station_Id = getIntent().getStringExtra("STATION_ID");
        Station_Name = getIntent().getStringExtra("STATION_NAME");
        queue_Availability = getIntent().getStringExtra("AvailabilityStation");

        branch_name.setText(Station_Name);
        availability.setText(queue_Availability);
        total_vehicleAmount.setText(String.valueOf(Total_vehicles));
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueueDetails.this, AvailableStationListActivity.class);
                startActivity(intent);
            }
        });


        join_Queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IFuelQueueApi fuelQueueApi = APIConfig.getConfig().create(IFuelQueueApi.class);
                DBHelper dbHelper = new DBHelper(QueueDetails.this);
                User user = dbHelper.getSingleUser();

                QueueCustomer queueCustomer = new QueueCustomer(user.getUserId(), true, "", "", "", "");

                FuelQueueRequest fuelQueueRequest = new FuelQueueRequest(Station_Id, 0, queueCustomer);
                System.out.println("" + "+6" + fuelQueueRequest.getCustomers().getUserId());
                Call<FuelQueueResponse> call = fuelQueueApi.AddUserFuelQueue(Station_Id, fuelQueueRequest);

                call.enqueue(new Callback<FuelQueueResponse>() {
                    @Override
                    public void onResponse(Call<FuelQueueResponse> call, Response<FuelQueueResponse> response) {
                        //display a error message if response unsuccessful

                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        FuelQueueResponse fuelQueueResponses = response.body();
                        for (QueueCustomer queueCustomer : fuelQueueResponses.getCustomers()) {
                            title.add(queueCustomer.getVehicleType());

                            System.out.println(fuelQueueResponses);
                        }
                        for (String sub : title) {
                            subTitle.add(Collections.frequency(title, sub));
                        }

                        Total_vehicles = fuelQueueResponses.getNumberOfVehicles();


                    }

                    @Override
                    public void onFailure(Call<FuelQueueResponse> call, Throwable t) {

                    }
                });


            }
        });
        queue_vehicle_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QueueDetailsVehicalTypesListActivity.class);
                intent.putStringArrayListExtra("Title",title);
                intent.putIntegerArrayListExtra("subTitle",subTitle);
                startActivity(intent);
                finish();
            }
        });


    }}