package com.example.yumplanner.data.dataSource.home.remote;

import com.example.yumplanner.data.model.DetailMeal;

public interface RandomMeaDataResponse {
    void onSuccess(DetailMeal detailMeal);
    void onError(String message);
}
