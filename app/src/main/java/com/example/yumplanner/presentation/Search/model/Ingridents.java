package com.example.yumplanner.presentation.Search.model;

public class Ingridents implements SearchableItems {
    String name;
    String image;
    public  Ingridents(String name){
        this.name=name;
    }
    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
