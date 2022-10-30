package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.user.IUserApi;
import com.example.fuel_queue_client.database.DBHelper;
import com.example.fuel_queue_client.models.user.User;
import com.example.fuel_queue_client.models.user.UserResponse;

import java.security.acl.Owner;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerProfileActivity extends AppCompatActivity {

    Button Create,View,LogOut, deleteBtn;
    DBHelper dbHelper = new DBHelper(OwnerProfileActivity.this);

    public void removeUserDetails() {
        //directs to the main activity
        Intent intent = new Intent(OwnerProfileActivity.this, MainActivity.class);
        User user = dbHelper.getSingleUser();
        //deletes the locally saved user
        dbHelper.deleteOne(user.getId());
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_owner_profile);

        Create=findViewById(R.id.Create_station_Owner);
        View = findViewById(R.id.View_stations_Owner);
        LogOut =findViewById(R.id.LogOUT_Owner);
        deleteBtn = findViewById(R.id.profile_delete_owner);

        //directs to the fuel station registering interface
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(OwnerProfileActivity.this, RegisterStationActivity.class);
                startActivity(intent);
            }
        });

        //directs to the fuel station list interface
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(OwnerProfileActivity.this, FuelStationListActivity.class);
                startActivity(intent);
            }
        });

        //deletes the locally saved user and directs to the main activity
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                DBHelper dbHelper = new DBHelper(OwnerProfileActivity.this);

                Intent intent = new Intent(OwnerProfileActivity.this, MainActivity.class);
                User user = dbHelper.getSingleUser();
                dbHelper.deleteOne(user.getId());
                startActivity(intent);
                finish();
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