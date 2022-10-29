package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.user.User;

import java.security.acl.Owner;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button explore;
    TextView register;
    DBHelper dbHelper = new DBHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        User user = dbHelper.getSingleUser();

        System.out.println(user);

        if(user != null && Objects.equals(user.getRole(), "customer")) {
            finish();
            Intent intent = new Intent(getApplicationContext(), CustomerProfileActivity.class);
            startActivity(intent);
        } else if (user != null && Objects.equals(user.getRole(), "station-owner")) {
            finish();
            Intent intent = new Intent(getApplicationContext(), OwnerProfileActivity.class);
            startActivity(intent);
        }

        explore = findViewById(R.id.exploreBtn);
        register = findViewById(R.id.register);

        //directs to the login page
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //directs to the register page
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}