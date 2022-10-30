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

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.auth.IAuthApi;
import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.user.UserRequest;
import com.example.fuel_queue_client.models.user.UserResponse;
import com.example.fuel_queue_client.utils.InputValidator;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ImageView backBtn;
    Switch stationOwnerSwitch;
    Button loginBtn;
    EditText usernameInput, passwordInput;

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

        //validate user and if not valid, displays a error message
        loginBtn.setOnClickListener(view -> {
            //extract values from form fields
            username = usernameInput.getText().toString();
            password = passwordInput.getText().toString();

            // checks whether all fields are filled
            if (username.length() <= 0 || password.length() <= 0) {
                Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                // validate the username
                boolean isValidUsername = InputValidator.usernameValidate(username);

                // make request to login the user
                IAuthApi authApi = APIConfig.getConfig().create(IAuthApi.class);
                Call<UserResponse> call = authApi.loginUser(new UserRequest("", username, password, role, ""));

                if(isValidUsername) {
                    /***
                     Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response
                     ***/
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            //display a error message if response unsuccessful
                            if (!response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Your username or password is invalid", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            DBHelper dbHelper = new DBHelper(LoginActivity.this);
                            //get the response of successful request
                            UserResponse userResponse = response.body();

                            //displays a error message if response is null
                            if (userResponse == null) {
                                Toast.makeText(LoginActivity.this, "Your username or password is invalid", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //save user inside the local database
                            boolean result = dbHelper.saveUser(userResponse.getId(),
                                    userResponse.getUsername(),
                                    userResponse.getRole());

                            //displays error message if user saving is not successful
                            if (!result) {
                                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Intent intent;

                            //direct to the relevant user profile according to the user type
                            if (Objects.equals(role, "customer")) {
                                intent = new Intent(LoginActivity.this, CustomerProfileActivity.class);
                            } else {
                                intent = new Intent(LoginActivity.this, OwnerProfileActivity.class);
                            }

                            startActivity(intent);
                            //displays  success message on successful response
                            Toast.makeText(getApplicationContext(), "Welcome back", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        //displays toast message,if response of the request is a failure
                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // displays error message if  username is not valid
                    Toast.makeText(LoginActivity.this, "Enter a valid username", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //direct to the home page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //allocate user type using switch.if checked, user type is equals to station-owner. else user type is customer.
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