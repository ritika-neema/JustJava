package com.example.android.justjava;

/**
 * Created by uswer on 4/22/2018.
 */

public class DataModel {
    String name;
    int id_;
    int image;

    public DataModel(String name, int id_, int image) {
        this.name = name;
        this.id_ = id_;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }
}
