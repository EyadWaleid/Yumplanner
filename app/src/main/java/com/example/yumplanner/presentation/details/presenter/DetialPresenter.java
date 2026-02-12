package com.example.yumplanner.presentation.details.presenter;

import android.content.Context;

import com.example.yumplanner.data.home.model.DetialMeal;

public interface DetialPresenter {
    void getData(DetialMeal meal);
    void getDataById(String id);
    void addCalender();
    void onDateSelected(String date);
    void OnSaveVMeal(Context context);
    void favMeal();
    void cancelCalender();

    void clear();


}
