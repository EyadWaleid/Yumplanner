package com.example.yumplanner.presentation.Search.model;

public class Category implements  SearchableItems{
    String name;
    String image;
    public Category(String name){
        this.name=name;
    }
    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public int getItemType() {
        return 3;
    }
}
