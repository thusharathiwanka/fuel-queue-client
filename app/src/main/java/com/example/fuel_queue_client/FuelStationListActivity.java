package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;

public class FuelStationListActivity extends AppCompatActivity {

    ListView listView;
    String[] title = {"Github App", "Gmail App", "PlayStore App", "Kik App", "Meetme", "Yahoo", "Drive"};
    String[] subTitle = {"Download Github App", "Download Gmail App", "Download PlayStore App", "Download Kik App",
            "Download Meetme", "Download Yahoo", "Download GoogleDrive"};
    Integer[] imageID ={};
//    {R.drawable.github,R.drawable.gmail,R.drawable.play,R.drawable.kik,R.drawable.meetme,
//            R.drawable.yahoo,R.drawable.google};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_fuel_station_list);

        listView = findViewById(R.id.listView_id);
        ListViewAdapter adapter = new ListViewAdapter(FuelStationListActivity.this, title,subTitle,imageID);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FuelStationListActivity.this, "Thanks For Download App= "+title[position], Toast.LENGTH_SHORT).show();


            }
        });
    }
}