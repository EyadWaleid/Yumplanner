package com.example.yumplanner.data.model;

import com.google.gson.annotations.SerializedName;


public class Meal {
    @SerializedName("strMeal")
    private String mealName;
    @SerializedName("strMealThumb")
    private  String mealUrl;
    @SerializedName("idMeal")
    private String mealId;
    public Meal(String mealName, String mealUrl, String mealId) {
        this.mealName = mealName;
        this.mealUrl = mealUrl;
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealUrl() {
        return mealUrl;
    }

    public void setMealUrl(String mealUrl) {
        this.mealUrl = mealUrl;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
    /*
    {
        "strMeal": "Arroz con gambas y calamar",
        "strMealThumb": "https://www.themealdb.com/images/media/meals/jc6oub1763196663.jpg",
        "idMeal": "53147"
        },
    * */
}
