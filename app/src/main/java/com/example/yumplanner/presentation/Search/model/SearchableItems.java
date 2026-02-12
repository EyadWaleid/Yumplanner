package com.example.yumplanner.presentation.Search.model;

public class SearchableItems {
    String name;
    String imageUrl;
    public SearchableItems(String name, String image) {
        this.name = name;
        this.imageUrl = image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}