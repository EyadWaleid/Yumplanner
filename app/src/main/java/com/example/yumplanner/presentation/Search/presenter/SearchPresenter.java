package com.example.yumplanner.presentation.Search.presenter;

import com.example.yumplanner.presentation.Search.model.SearchableItems;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SearchPresenter {
    void getCategoryList();
    void getIngredientList();
    void getAreaList();
    void searchData(Observable<String> searchQuery);
    public void toSearchResult(String name,String type) ;
    public  void searchByMealName(Observable<String> searchQuery);
    public  void onDestroy();}