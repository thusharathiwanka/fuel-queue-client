package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.fuel_station.IFuelStationApi;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableStationListActivity extends AppCompatActivity {

    ListView listView;
    List<String> title = new ArrayList<String>();
    List<String> subTitle = new ArrayList<String>();
    String[] list_title;
    String[] list_subtitle;
    Integer[] imageID ={};


// retrieve all available stations from database.
    IFuelStationApi fuelStationApi = APIConfig.getConfig().create(IFuelStationApi.class);
    Call<List<FuelStationResponse>> call = fuelStationApi.GetAllStations();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_available_sation_list);


        /***
         Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response
         ***/
        call.enqueue(new Callback<List<FuelStationResponse>>() {
            @Override
            public void onResponse(Call<List<FuelStationResponse>> call, Response<List<FuelStationResponse>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                //get the response of success request
                List<FuelStationResponse> stations = response.body();

                //iterate through the response list and identify available stations
                for (FuelStationResponse fuelStationResponse : stations) {

                    if(fuelStationResponse.getAvailability() != "Available"){

                    //name of the station as title of the list item
                    title.add(fuelStationResponse.getName());

                    //name of the station as subTitle of the list item
                    subTitle.add(fuelStationResponse.getLocation());

                    }
                }

                //conversion of arraylist to string array
                list_title= title.toArray(new String[0]);
                list_subtitle = subTitle.toArray(new String[0]);;


                listView = findViewById(R.id.listView_id);
                //set the list view to the adapter
                ListViewAdapter adapter = new ListViewAdapter(AvailableStationListActivity.this,list_title ,list_subtitle,imageID);
                listView.setAdapter(adapter);

                //after click on one list item,moves to the queue details page of each the station
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //pass the station Id as a intent to the Queue details activity
                        Intent intent = new Intent(AvailableStationListActivity.this, QueueDetails.class);
                        intent.putExtra("STATION_ID",stations.get(position).getId());
                        startActivity(intent);


                    }
                });

            }

            //displays toast message,if response of the request is a failure
            @Override
            public void onFailure(Call<List<FuelStationResponse>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}