package com.example.earthteamproject;

import java.io.Serializable;

public class Produce implements Serializable {
    private String name;
    private String descriptionShort;
    private String descriptionLong;
    private String imgURL;
    private Double seasonRange;

    public Produce(String name, String descriptionLong, String descriptionShort, String imgURL, Double seasonRange){
        this.name = name;
        this.descriptionLong = descriptionLong;
        this.descriptionShort = descriptionShort;
        this.imgURL = imgURL;
        this.seasonRange = seasonRange;
    }

    public Produce(){this(null,null,null,null,null); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Double getSeasonRange() {
        return seasonRange;
    }

    public void setSeasonRange(Double seasonRange) {
        this.seasonRange = seasonRange;
    }

    //TODO format this so it will be more useful : ^ )
    public String toString() {
        return "Produce{" +
                "name='" + name + '\'' +
                ", descriptionShort='" + descriptionShort + '\'' +
                ", descriptionLong='" + descriptionLong + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", seasonRange=" + seasonRange +
                '}';
    }
}
