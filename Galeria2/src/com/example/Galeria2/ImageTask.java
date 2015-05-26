package com.example.Galeria2;

import android.content.ContentValues;

/**
 * Created by Adam on 2015-05-26.
 */
public class ImageTask {
    private String name;
    private String address;
    private Float rating;
    private Float latitude;
    private Float longitude;

    public ImageTask(String name, String address, Float rating, Float latitude, Float longitude) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
