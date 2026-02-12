package com.example.yumplanner.data.network.response;

import com.example.yumplanner.data.home.model.dto.IngredientDto;

import java.util.List;

public class IngredientResponse {
    List<IngredientDto> meals;
    public  List<IngredientDto> getIngredients (){return meals ; }
}
