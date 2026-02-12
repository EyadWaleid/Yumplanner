package com.example.yumplanner.data.network.response;

import com.example.yumplanner.data.home.model.dto.DetailMealModelDto;

import java.util.List;

public class DetailMealResponse {
    List<DetailMealModelDto> meals;
    public  List<DetailMealModelDto> getMeals(){return  meals ;}
}
