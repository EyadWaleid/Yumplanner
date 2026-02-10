package com.example.yumplanner.data.home.datasource.remote;

import com.example.yumplanner.data.home.model.dto.DetailMealModelDto;

public interface RandomMeaDataResponse {
    void onSuccess(DetailMealModelDto detailMealModelDto);
    void onError(String message);
}
