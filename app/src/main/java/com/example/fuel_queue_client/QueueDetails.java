package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class QueueDetails extends AppCompatActivity {
    ImageView backBtn;
    Button joinQueue;
    TextView branchName,queueAvailability ;
    EditText totalVehicleAmount,vehicleName,vehicleAmount,departureTime,fuelType,fuelTypeStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);
        backBtn = findViewById(R.id.back);
        joinQueue = findViewById(R.id.joinQueue);
        branchName = findViewById(R.id.branchName);
        queueAvailability = findViewById(R.id.queueAvailability);
        totalVehicleAmount = findViewById(R.id.totalVehicleAmount);
        vehicleName = findViewById(R.id.vehicleName);
        vehicleAmount = findViewById(R.id.vehicleAmount);
        departureTime = findViewById(R.id.departureTime);
        fuelType = findViewById(R.id.fuelType);
        fuelTypeStatus = findViewById(R.id.fuelTypeStatus);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });






    }
}