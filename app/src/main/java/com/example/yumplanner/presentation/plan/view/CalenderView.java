package com.example.yumplanner.presentation.plan.view;

import com.example.yumplanner.data.home.model.DetialMeal;

public interface CalenderView {
    void setImage(String imageUrl);
    void setText(String mealName );
    void showData();
    void hideData();
    void showNoData();
    void hideNoData();
    void showSuccessSnackBar();
    void toDetail(DetialMeal detailMeal);
    void removeDeletICon();
    void adddDeleteICon();
}
