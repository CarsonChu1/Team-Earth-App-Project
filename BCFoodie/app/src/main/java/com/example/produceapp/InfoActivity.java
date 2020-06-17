package com.example.produceapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public class InfoActivity extends AppCompatActivity {

    ImageView image;

    TextView name, price, description, season;


    public InfoActivity() throws JSONException {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);//Sets view of the activity
        image = findViewById(R.id.imageDetail);
        name = findViewById(R.id.nameTextDetail);
        price = findViewById(R.id.priceDetail);
        description = findViewById(R.id.descDetail);
        season = findViewById(R.id.seasonDetail);
        ProduceInfo produce = MainActivity.lastProduce;
        image.setImageResource(produce.getImage());
        season.setText(produce.getSeasonText());//Uses produceInfo method method to get name
        name.setText(produce.getName());//Uses produceInfo method method to get name
        description.setText(produce.getDescLong());//Uses produceInfo method method to get description
        price.setText(produce.getPrice());

        
    }





}
