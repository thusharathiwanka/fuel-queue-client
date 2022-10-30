package com.example.fuel_queue_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QueueDetailsVehicalTypesListActivity extends AppCompatActivity {

    ListView listView;
    String[] list_title;
    String[] list_subtitle;
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<Integer> subTitle = new ArrayList<Integer>();

    Integer[] imageID ={};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details_vehical_types_list);


        title = getIntent().getStringArrayListExtra("Title");
        subTitle = getIntent().getIntegerArrayListExtra("subTitle");
        //conversion of arraylist to string array
        list_title= title.toArray(new String[0]);
        list_subtitle = subTitle.toArray(new String[0]);;

        listView = findViewById(R.id.listView_id);
        //set the list view to the adapter
        ListViewAdapter adapter = new ListViewAdapter(QueueDetailsVehicalTypesListActivity.this,list_title ,list_subtitle,imageID);
        listView.setAdapter(adapter);

    }
}