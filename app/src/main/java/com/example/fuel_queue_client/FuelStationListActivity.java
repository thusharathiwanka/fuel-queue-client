package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fuel_queue_client.api.APIConfig;
import com.example.fuel_queue_client.api.fuel_station.IFuelStationApi;
import com.example.fuel_queue_client.models.fuel_station.FuelStationRequest;
import com.example.fuel_queue_client.models.fuel_station.FuelStationResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelStationListActivity extends AppCompatActivity {

    IFuelStationApi fuelStationApi = APIConfig.getConfig().create(IFuelStationApi.class);
    Call<List<FuelStationResponse>> call = fuelStationApi.GetAllStations();
    ListView listView;
    List<String> title = new ArrayList<String>();
    List<String> subTitle = new ArrayList<String>();
    String[] list_title;
    String[] list_subtitle;
    Integer[] imageID ={};
//    {R.drawable.github,R.drawable.gmail,R.drawable.play,R.drawable.kik,R.drawable.meetme,
//            R.drawable.yahoo,R.drawable.google};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_fuel_station_list);



        call.enqueue(new Callback<List<FuelStationResponse>>() {
            @Override
            public void onResponse(Call<List<FuelStationResponse>> call, Response<List<FuelStationResponse>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                 List<FuelStationResponse> stations = response.body();

                for (FuelStationResponse fuelStationResponse : stations) {
                    title.add(fuelStationResponse.getName());
                     subTitle.add(fuelStationResponse.getLocation());
                     System.out.println("title" +title);
                    System.out.println("title" +subTitle);
                }

                 list_title= title.toArray(new String[0]);
                 list_subtitle = subTitle.toArray(new String[0]);;
                System.out.println("title 02" +list_title);
                System.out.println("list_subtitle 02" +list_subtitle);

                listView = findViewById(R.id.listView_id);
                ListViewAdapter adapter = new ListViewAdapter(FuelStationListActivity.this,list_title ,list_subtitle,imageID);
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(FuelStationListActivity.this, "Thanks For Download App= "+list_title[position], Toast.LENGTH_SHORT).show();


                    }
                });

            }

            @Override
            public void onFailure(Call<List<FuelStationResponse>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}