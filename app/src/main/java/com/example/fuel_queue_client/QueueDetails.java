package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.fuel_queue.IFuelQueueApi;
import com.example.fuel_queue_client.models.fuel_queue.FuelQueueRequest;
import com.example.fuel_queue_client.models.fuel_queue.FuelQueueResponse;
import com.example.fuel_queue_client.models.fuel_queue.QueueCustomer;

import java.util.Objects;

import retrofit2.Call;

public class QueueDetails extends AppCompatActivity {
    ImageView backBtn;
    Button joinQueue;
    TextView branchName,queueAvailability ;
    EditText totalVehicleAmount,vehicleName,vehicleAmount,departureTime,fuelType,fuelTypeStatus;
    String Station_Id;
    int Total_vehicals;
    QueueCustomer queueCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_queue_details);

        backBtn = findViewById(R.id.back);
        joinQueue = findViewById(R.id.joinQueue);
        branchName = findViewById(R.id.branchName);
        queueAvailability = findViewById(R.id.queueAvailability);
        totalVehicleAmount = findViewById(R.id.totalVehicleAmount);
        vehicleName = findViewById(R.id.vehicleName);
        vehicleAmount = findViewById(R.id.vehicleAmount);
        departureTime = findViewById(R.id.departureTime);

        Station_Id =getIntent().getStringExtra("STATION_ID");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueueDetails.this, AvailableStationListActivity.class);
                startActivity(intent);
            }
        });


        IFuelQueueApi fuelQueueApi = APIConfig.getConfig().create(IFuelQueueApi.class);
        Total_vehicals = Integer.parseInt(totalVehicleAmount.getText().toString());
//        new QueueCustomer();

//        FuelQueueRequest fuelQueueRequest = new FuelQueueRequest(Station_Id,Total_vehicals,)
//        Call<FuelQueueResponse> call = fuelQueueApi.AddUserFuelQueue(Station_Id,);
        joinQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });





    }
}