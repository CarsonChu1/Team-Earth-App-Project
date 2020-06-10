package com.example.earthteamproject;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.time.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {



    //arrays to contain data
    String s1[], s2[];

    int images[] = {R.drawable.strawberry,
            R.drawable.apple,
            R.drawable.celeries,
            R.drawable.kales,
            R.drawable.tomato,
            R.drawable.blackberry,
            R.drawable.beet,
            R.drawable.potato
    };

    //recyler view object
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        Produce eggplant = new Produce();


        //TODO Add proper exception handling code
        try {
            InputStream inputStream = res.openRawResource(R.raw.eggplant);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
             eggplant = (Produce) objectInputStream.readObject();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        //initalize recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //get string arrays from strings.xml
        s1 = getResources().getStringArray(R.array.produce);
        s2 = getResources().getStringArray(R.array.descriptions);
        s1[7] = eggplant.getName();
        s2[7] = eggplant.getDescriptionShort();
        //images[8] = Integer.parseInt(eggplant.getImgURL());


        //intialize my adapter and sent data to its constructor
        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);

        recyclerView.setAdapter(myAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
