package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.user.User;

import java.util.Objects;

public class CustomerProfileActivity extends AppCompatActivity {

    Button View_list, Logout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_customer_profile);

        View_list = findViewById(R.id.View_station_customer);
        Logout  = findViewById(R.id.LogOut_Customer);

        View_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerProfileActivity.this, AvailableSationListActivity.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(CustomerProfileActivity.this);

                Intent intent = new Intent(CustomerProfileActivity.this, MainActivity.class);
                User user = dbHelper.getSingleUser();

                startActivity(intent);

            }
        });

    }
}