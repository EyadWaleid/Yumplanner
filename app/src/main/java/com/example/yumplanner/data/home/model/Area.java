package com.example.yumplanner.data.home.model;

public class Area {
    private String name ;
    private String ImgaeUrl;

    public Area(String name, String imgaeUrl) {
        this.name = name;
        ImgaeUrl = imgaeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgaeUrl() {
        return ImgaeUrl;
    }

    public void setImgaeUrl(String imgaeUrl) {
        ImgaeUrl = imgaeUrl;
    }
}
