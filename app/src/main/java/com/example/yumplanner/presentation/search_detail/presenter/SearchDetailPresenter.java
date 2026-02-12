package com.example.yumplanner.presentation.search_detail.presenter;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public interface SearchDetailPresenter {
    void getMealsData(Map<String,String> map);
    void toDetailScreen(String id );
    void searchData(Observable<String> searchQuery);

}
