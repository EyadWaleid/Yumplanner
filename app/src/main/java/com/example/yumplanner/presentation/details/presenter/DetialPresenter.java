package com.example.yumplanner.presentation.details.presenter;

import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;

public interface DetialPresenter {
    void getData(DetialMealDTO meal);
    void getDataById(String id);
    void addCalender();



}
