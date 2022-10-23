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

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    ImageView backBtn;
    Button regBtn;
    EditText usernameInput, emailInput, passwordInput, vehicleTypeInput;
    Switch stationOwnerSwitch;

    String username;
    String password;
    String email;
    String role;
    String vehicleType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);

        backBtn = findViewById(R.id.back);
        emailInput = findViewById(R.id.emailInput);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        vehicleTypeInput = findViewById(R.id.vehicleTypeInput);
        stationOwnerSwitch = findViewById(R.id.ownerSwitchReg);
        regBtn = findViewById(R.id.RegisterBtn);

        regBtn.setOnClickListener(view -> {
            email = emailInput.getText().toString();
            username = usernameInput.getText().toString();
            password = passwordInput.getText().toString();
            vehicleType = vehicleTypeInput.getText().toString();

            if(email.length() <= 0 || username.length() <= 0 || password.length() <= 0) {
                Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }else {
                boolean isValidEmail  = emailValidate(email);

                if(isValidEmail) {
                    boolean isValidUsername = usernameValidate(username);

                    if(isValidUsername) {
                        if(true) {
                            Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_SHORT).show();
                        } else if(false) {
                            Toast.makeText(getApplicationContext(), "This username is already taken", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter a valid username. Username should contain characters and numbers", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stationOwnerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    vehicleTypeInput.setVisibility(View.INVISIBLE);
                    role = "station-owner";
                } else {
                    vehicleTypeInput.setVisibility(View.VISIBLE);
                    role = "customer";
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static boolean emailValidate(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);

        return emailMatcher.find();
    }

    public static boolean usernameValidate(String username) {
        String usernameRegex = "^[aA-zZ0-9_-]\\w{5,30}$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(username);

        return usernameMatcher.matches();
    }
}