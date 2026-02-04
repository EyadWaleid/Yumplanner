package com.example.yumplanner.presentation.Search.model;

public class Catagorey implements  SearchableItems{
    String name;
    String image;
    public  Catagorey(String name){
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
