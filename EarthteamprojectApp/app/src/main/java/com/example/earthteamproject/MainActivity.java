package com.example.earthteamproject;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.time.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {



    //s1 array holds names, s2 holds descriptions
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

    //RecyclerView object declaration
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        Produce eggplant = new Produce();
        Produce apple = new Produce("apple", "this is an apple","an apple","R.drawable.apple", (double) 0406);


        try {
            InputStream inputStream = res.openRawResource(R.raw.eggplant);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
             eggplant = (Produce) objectInputStream.readObject();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        //initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);

        //get string arrays from strings.xml
        s1 = getResources().getStringArray(R.array.produce);
        s2 = getResources().getStringArray(R.array.descriptions);
        s1[7] = apple.getName();
        s2[7] = apple.getDescriptionShort();
        s1[7] = eggplant.getName();


        //Initialize MyAdapter and sent data to its constructor
        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);

        recyclerView.setAdapter(myAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
