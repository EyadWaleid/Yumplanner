package com.example.yumplanner.presentation.Home.presenter;

import android.content.Context;

import com.example.yumplanner.utiles.connectivity.NetworkStatusListener;

public interface HomePresenter extends NetworkStatusListener {
    void getRandomData();
    void reachDetails();
    void toDessertDetail(String id );
    void onDestroy();
}
