package com.example.yumplanner.presentation.Search.presenter;

import com.example.yumplanner.presentation.Search.model.SearchableItems;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SearchPresenter {
    void getCategoryList();
    void getIngredientList();
    void getAreaList();
    void searchData(Observable<String> searchQuery);
    void toSearchResult(String name,String type) ;
/*
     void searchByMealName(Observable<String> searchQuery);
*/
     void onDestroy();
    void getMeals();}