package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fuel_queue_client.utils.InputValidator;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    ImageView backBtn;
    Switch stationOwnerSwitch;
    Button loginBtn;
    EditText usernameInput, passwordInput;

    InputValidator inputValidator;

    String username;
    String password;
    String role = "customer";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);

        backBtn = findViewById(R.id.back);
        loginBtn = findViewById(R.id.loginBtn);
        usernameInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        stationOwnerSwitch = findViewById(R.id.ownerSwitch);

        loginBtn.setOnClickListener(view -> {
            username = usernameInput.getText().toString();
            password = passwordInput.getText().toString();

            System.out.println(username + password + role);

            if (username.length() <= 0 || password.length() <= 0) {
                Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean isValidUsername = inputValidator.usernameValidate(username);

                if (isValidUsername) {
                    if (true) {
                        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                        intent.putExtra("USERNAME", username);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Welcome back " + username, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Your username or password is invalid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Enter a valid username", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        stationOwnerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    role = "station-owner";
                } else {
                    role = "customer";
                }
            }
        });
    }
}