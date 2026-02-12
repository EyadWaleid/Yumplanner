package com.example.yumplanner.presentation.Home.view;

import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.home.model.Meal;

import java.util.List;

public interface HomeView {
    void showError();

    void hideBackground();

    void showBackground();

    void showLoading();

    void hideLoading();

    void setSpecialMeal(String mealTitle, String imageMeal);

    void setDessert(List<Meal> desserts);

    void toDetial(DetialMeal detailMeal);

    void toDessertDetial(String id);

    void showNetworkError();

    void hidNetworkError();
    void setChefName(String name);

}
