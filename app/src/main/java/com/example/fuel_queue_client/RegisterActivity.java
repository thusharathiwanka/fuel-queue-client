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
import com.example.fuel_queue_client.api.auth.AuthApi;
import com.example.fuel_queue_client.api.auth.IAuthApi;
import com.example.fuel_queue_client.models.user.UserRequest;
import com.example.fuel_queue_client.models.user.UserResponse;
import com.example.fuel_queue_client.utils.InputValidator;

import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    ImageView backBtn;
    Button regBtn;
    EditText usernameInput, emailInput, passwordInput, vehicleTypeInput;
    Switch stationOwnerSwitch;

    String username;
    String password;
    String email;
    String role = "customer";
    String vehicleType = null;

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

            if (email.length() <= 0 || username.length() <= 0 || password.length() <= 0 || vehicleType.length() <= 0) {
                Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean isValidEmail = InputValidator.emailValidate(email);

                if (isValidEmail) {
                    boolean isValidUsername = InputValidator.usernameValidate(username);

                    IAuthApi authApi = APIConfig.getConfig().create(IAuthApi.class);
                    Call<UserResponse>  call = authApi.registerUser(new UserRequest(email, username, password, role, vehicleType));

                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (!response.isSuccessful()) {
                               Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                               return;
                            }

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stationOwnerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
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

    public JSONObject handleSubmit () {
        return null;
    }
}