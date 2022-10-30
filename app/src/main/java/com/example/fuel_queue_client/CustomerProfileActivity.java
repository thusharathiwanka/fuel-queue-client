package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.auth.IAuthApi;
import com.example.fuel_queue_client.api.user.IUserApi;
import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.user.User;
import com.example.fuel_queue_client.models.user.UserRequest;
import com.example.fuel_queue_client.models.user.UserResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerProfileActivity extends AppCompatActivity {

    Button View_list, Logout, deleteBtn;
    //creates a db helper object for current activity
    DBHelper dbHelper = new DBHelper(CustomerProfileActivity.this);

    public void removeUserDetails() {
        //directs to the main activity
        Intent intent = new Intent(CustomerProfileActivity.this, MainActivity.class);
        User user = dbHelper.getSingleUser();
        //deletes the locally saved user
        dbHelper.deleteOne(user.getId());
        startActivity(intent);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_customer_profile);


        View_list = findViewById(R.id.View_station_customer);
        Logout  = findViewById(R.id.LogOut_Customer);
        deleteBtn = findViewById(R.id.profile_delete_customer);

        // directs to the available fuel station list
        View_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerProfileActivity.this, AvailableStationListActivity.class);
                startActivity(intent);
            }
        });



        // directs to the main page and deletes the locally saved user object
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeUserDetails();
            }
        });

        deleteBtn.setOnClickListener(view -> {
            // make request to login the user
            IUserApi userApi = APIConfig.getConfig().create(IUserApi.class);
            Call<UserResponse> call = userApi.DeleteUser(dbHelper.getSingleUser().getUserId());

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Something went wrong when deleting your account", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(getApplicationContext(), "Your account has been successfully deleted", Toast.LENGTH_SHORT).show();
                    removeUserDetails();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    System.out.println(t.getMessage());
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}