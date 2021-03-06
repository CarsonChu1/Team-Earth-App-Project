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
    public String getSeasonText(){
        String textSeason = "In season during: ";
        if (this.season.contains("0"))
            textSeason = textSeason + "Winter, ";

        if(this.season.contains("1"))
            textSeason = textSeason + "Spring, ";

        if(this.season.contains("2"))
            textSeason = textSeason + "Summer, ";

        if(this.season.contains("3"))
            textSeason = textSeason + "Fall ";

        return textSeason;
    }


}
