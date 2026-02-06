package com.example.yumplanner.presentation.Home.view;

import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;
import com.example.yumplanner.data.model.Meal;

import java.util.List;

public interface HomeView {
    public void showError();
    public void showLoading();
    public  void hideLoading();
    public  void setSpecialMeal(String mealTitle,String imageMeal);
    public  void setDessert(List<Meal> desserts);
    void toDetial(DetialMealDTO detailMeal);

}
