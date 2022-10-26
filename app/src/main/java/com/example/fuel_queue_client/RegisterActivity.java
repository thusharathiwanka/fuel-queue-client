package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.auth.IAuthApi;
import com.example.fuel_queue_client.models.user.UserRequest;
import com.example.fuel_queue_client.models.user.UserResponse;
import com.example.fuel_queue_client.utils.InputValidator;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView backBtn;
    Button regBtn;
    EditText usernameInput, emailInput, passwordInput;
    Switch stationOwnerSwitch;
    Spinner vehicleTypeSpinner;

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
        stationOwnerSwitch = findViewById(R.id.ownerSwitchReg);
        vehicleTypeSpinner = findViewById(R.id.spinnerVehicle);
        regBtn = findViewById(R.id.RegisterBtn);

        //create element from default resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_types, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpinner.setAdapter(adapter);
        vehicleTypeSpinner.setOnItemSelectedListener(this);

        //direct to the login page if user verification is successful. if not displays a toast message.
        regBtn.setOnClickListener(view -> {
            //assign extracted values from form fields to variables
            email = emailInput.getText().toString();
            username = usernameInput.getText().toString();
            password = passwordInput.getText().toString();

            //if all required fields are not filled by the user shows a toast message with warning
            if (email.length() <= 0 || username.length() <= 0 || password.length() <= 0 || vehicleType.length() <= 0) {
                Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {

                //checks the validity of the user entered email
                boolean isValidEmail = InputValidator.emailValidate(email);

                if (isValidEmail) {
                    //check the validity of username if user entered email is valid.
                    boolean isValidUsername = InputValidator.usernameValidate(username);

                    //create auth api object
                    IAuthApi authApi = APIConfig.getConfig().create(IAuthApi.class);
                    // call the resgisterUser method to save the user
                    Call<UserResponse>  call = authApi.registerUser(new UserRequest(email, username, password, role, vehicleType));

                    /***
                     Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response
                     ***/
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            //display a toast message,if the response is not successful
                            if (!response.isSuccessful()) {
                               Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                               return;
                            }

                            //direct to login page if the response is successful
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            //displays success toast message
                            Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        //if any failure occurred, a toast message will appear
                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    //display error message if email is not valid
                    Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //allocate user type using the switch.If checked user type will be station-owner else customer.
        stationOwnerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vehicleTypeSpinner.setVisibility(View.INVISIBLE);
                    role = "station-owner";
                } else {
                    vehicleTypeSpinner.setVisibility(View.VISIBLE);
                    role = "customer";
                }
            }
        });

        //directs back to homepage
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         vehicleType = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}