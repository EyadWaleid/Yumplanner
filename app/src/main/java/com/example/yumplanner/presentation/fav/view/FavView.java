package com.example.yumplanner.presentation.fav.view;

import com.example.yumplanner.data.home.model.DetialMeal;

import java.util.List;

public interface FavView {
    void setData(List<DetialMeal> detialMeals);
    void goToDetails(DetialMeal detialMeal);
    void showLoader();
    void hideLoader();
    void showBackGround();
    void showNoData();
    void hidNoData();
    void hideBackGround();
    void showFavSnackBar(String message);
    void updateNetworkState(boolean isConnected);}
