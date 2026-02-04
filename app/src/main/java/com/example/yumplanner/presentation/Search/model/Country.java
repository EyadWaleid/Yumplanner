package com.example.yumplanner.presentation.Search.model;

public class Country implements SearchableItems{
    String name;
    String image;
    public  Country(String name){
        this.name=name;
    }
    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
