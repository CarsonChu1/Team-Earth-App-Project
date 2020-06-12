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

public class MainActivity extends AppCompatActivity {
    public static ProduceInfo lastProduce;//Instance of last selected produce
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    ProduceInfo[] produces;//All the produces

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

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(produces, this);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }


}
