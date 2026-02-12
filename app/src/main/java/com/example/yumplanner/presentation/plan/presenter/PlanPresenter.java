package com.example.yumplanner.presentation.plan.presenter;

import com.example.yumplanner.utiles.connectivity.NetworkStatusListener;

public interface PlanPresenter extends NetworkStatusListener {
    void getMeal(String date);
    void onDestroy();
    void deleteBtn();
    void goToDetails();
}
