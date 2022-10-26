package com.example.fuel_queue_client;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] title ;
    private final String[]  subtitle;
    private final Integer[] imageID;

    //initialization of  of adapter class
    public ListViewAdapter(Activity context, String[] title, String[] subtitle, Integer[] imageID) {
        super(context,R.layout.activity_custom_layout,title);
        this.context = context;
        this.title = title;
        this.subtitle = subtitle;
        this.imageID = imageID;
    }

    /***
     * return the list item view according to the passed values
     * @param  position - position of the item
     * @param view  -
     * @param parent -
     * @return - list item view
     * **/
    public View getView(int position, View view, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("viewHolder") View rootView = inflater.inflate(R.layout.activity_custom_layout,null,true);
        TextView titleTxt =rootView.findViewById(R.id.title_id);
        TextView subTitleTxt =rootView.findViewById(R.id.subtitle_id);
        ImageView imageView =rootView.findViewById(R.id.imag_id);

        titleTxt.setText(title[position]);
        subTitleTxt.setText(subtitle[position]);

        return rootView;
    }
}
