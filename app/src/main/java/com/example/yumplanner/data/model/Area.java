package com.example.yumplanner.data.model;

import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("strArea")
    private String contryName;

    public Area(String strArea) {
        this.contryName = strArea;
    }

    public String getStrArea() {
        return contryName;
    }
}
