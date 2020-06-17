package com.example.produceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.time.*;

public class MainActivity extends AppCompatActivity {
    public static ProduceInfo lastProduce;//Instance of last selected produce
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    ProduceInfo[] produces;//All the produces
    ProduceInfo[] filteredProduce;

    int currentMonth;
    char monthChar;


    public void loadJSONFromAsset(String fileName) {
        String json;
        try {
            InputStream is = this.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jObj = new JSONObject(json);//Creates JSON Object from the text
            JSONArray jArr = jObj.getJSONArray("produce");//Gets the array from the JSON object
            produces = new ProduceInfo[jArr.length()];
            for(int i = 0; i < jArr.length(); i++){//Loop that assigns the values from the names
                JSONObject produceObj = jArr.getJSONObject(i);
                String name = produceObj.getString("name");
                String descShort = produceObj.getString("descriptionShort");
                String descLong = produceObj.getString("descriptionLong");
                String image = produceObj.getString("imgURL");
                String season = produceObj.getString("seasonRange");
                String price = produceObj.getString("price");

                ProduceInfo produce = new ProduceInfo( //Filling in the ProduceInfo class's constructor
                        name,
                        descShort,
                        descLong,
                        getResources().getIdentifier(image , "drawable", getPackageName()),//Gets the normal images
                        season,
                        price
                );
                produces[i] = produce;//produces array at position i
            }
        } catch (JSONException | IOException ex) {
            Log.e("ERROR","BIG OOPS", ex);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadJSONFromAsset("produce.json");//Loads and assigns the values into the variables

        Instant instant = Instant.now();
        currentMonth = Integer.parseInt(instant.toString().substring(5,7));

        Log.i("dateSet", currentMonth+"");

        if(currentMonth == 12 || currentMonth == 1 || currentMonth == 2)
            monthChar = 48; //char value 0
        if(currentMonth == 3 || currentMonth == 4 || currentMonth == 5)
            monthChar = 49; // value 1
        if(currentMonth == 6 || currentMonth == 7 || currentMonth == 8)
            monthChar = 50; //value 2
        if(currentMonth == 9 || currentMonth == 10 || currentMonth == 11)
            monthChar = 51; //value 3

        Log.i("monthVarSet", monthChar +"");

        int filterLength = 0;
        for(int i = 0; i<produces.length; i++){
            if(produces[i].getSeason().contains(monthChar+"")){
                filterLength++;
            Log.i("monthFilter","success " + filterLength);}
        }

        filteredProduce = new ProduceInfo[filterLength];
        int successfulHits = 0;
        for(int i = 0; i<produces.length; i++){
            
            if(produces[i].getSeason().contains(monthChar +"")){
                filteredProduce[successfulHits] = produces[i];
            successfulHits++;}
        }





        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(filteredProduce, this);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }


}
