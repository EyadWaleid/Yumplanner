package com.example.yumplanner.presentation.details.view;

import com.example.yumplanner.data.home.model.Ingredient;

import java.util.List;

public interface DetialView {
    void setData(String image ,String mealName);
     void setIngredients(List<Ingredient> list);
    void setSteps(List<String>steps);
    void setVideo();
    void showLoader();
    void hideLoader();
    void hideView();
    void showView();

    void showError();
    void showCalender();



}
