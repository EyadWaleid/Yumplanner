package com.example.yumplanner.presentation.search_detail.view;

import com.example.yumplanner.data.home.model.Meal;

import java.util.List;

public interface SearchDetialView  {
    void setItemData(List<Meal> meals);
    void goDetials(String id);


}
