package com.example.yumplanner.presentation.details.presenter;

import com.example.yumplanner.data.home.model.DetialMeal;

public interface DetialPresenter {
    void getData(DetialMeal meal);
    void getDataById(String id);
    void addCalender();



}
