package com.example.yumplanner.presentation.Home.view;

import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.home.model.Meal;

import java.util.List;

public interface HomeView {
    public void showError();
    public  void hideBackground();
    public  void showBackground();
    public void showLoading();
    public  void hideLoading();
    public  void setSpecialMeal(String mealTitle,String imageMeal);
    public  void setDessert(List<Meal> desserts);
    void toDetial(DetialMeal detailMeal);
    void toDessertDetial(String id );
    void showNetworkError();
    void hidNetworkError();

}
