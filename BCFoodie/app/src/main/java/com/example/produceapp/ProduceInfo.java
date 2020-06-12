package com.example.produceapp;

import android.util.Log;

public class ProduceInfo {

    private final String name, descShort, descLong, price, season;
    private final int image;


    public ProduceInfo(String name, String descShort, String descLong, int image, String season, String price) {
        this.name = name;
        this.descShort = descShort;
        this.descLong = descLong;
        this.image = image;
        this.season = season;
        this.price = price;

    }

    public String getName(){
        return name;
    }
    public String getDescShort(){
        return descShort;
    }
    public String getDescLong(){
        return descLong;
    }
    public int getImage(){
        return image;
    }
    public String getSeason(){
        return season;
    }
    public String getPrice(){
        return price;
    }


}
