package com.example.yumplanner.presentation.details.view;

import com.example.yumplanner.data.home.model.MealIngredient;

import java.util.List;

public interface DetialView {
    void setData(String image ,String mealName);
     void setIngredients(List<MealIngredient> list);
    void setSteps(List<String>steps);
    void setVideo();
    void showLoader();
    void hideLoader();
    void hideView();
    void showView();
    String setDate();
    void fillIcon();
    void unFillIcon();
    void showError();
    void showCalender();
    void hideCalender();
    void showSuccessSnackbar();
    void showFailureSnackbar();



}
