package com.example.yumplanner.presentation.fav.presenter;

import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.utiles.connectivity.NetworkStatusListener;

public interface FavPresenter  extends NetworkStatusListener {
    void loadFavData();
    void deleteFavData(DetialMeal detialMeal);
    void goToDetails(DetialMeal detialMeal);
}
