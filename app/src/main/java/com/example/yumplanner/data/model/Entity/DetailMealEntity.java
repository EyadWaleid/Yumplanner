package com.example.yumplanner.data.model.Entity;

import com.example.yumplanner.data.home.model.Ingredient;

import java.util.List;

public class DetailMealEntity {
    private  String mealName;
    private String mealId;
    private  String category;
    private String area;
    private List<String> instructions;
    private  String imageUrl;
    private  boolean isFav;

    private List<Ingredient> ingredients;
}
