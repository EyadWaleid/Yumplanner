package com.example.yumplanner.presentation.Home.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;
import com.example.yumplanner.data.model.Meal;


import java.util.List;

/*
public class HomeViewModel extends ViewModel implements HomeView {

    private final MutableLiveData<DetailMeal> randomMeal = new MutableLiveData<>();
    private final HomePresenter presenter;

    public HomeViewModel() {
        presenter = new HomePresenterImp(this);
    }

    public LiveData<DetailMeal> getRandomMeal() {
        return randomMeal;
    }

    public void fetchRandomMeal() {
        if (randomMeal.getValue() != null) return;
        presenter.getRandomData();
    }


    @Override
    public void setData(DetailMeal detailMeal) {
        randomMeal.postValue(detailMeal);
    }

    @Override
    public void showError() {
        // optional: add error LiveData later
    }

    @Override public void showLoading() {

    }
    @Override public void hideLoading() {}
}*/
public class HomeViewModel extends ViewModel {
    DetialMealDTO detailMeal;

    private MutableLiveData<DetialMealDTO> mealLiveData = new MutableLiveData<>();
    private  MutableLiveData<List<Meal>> recommendedMeals=new MutableLiveData<>();

    public LiveData<DetialMealDTO> getMeal() {
        return mealLiveData;
    }
    public  LiveData<List<Meal>> getDessertMeal(){
        return  recommendedMeals;
    }
    public void  setDessert(List<Meal> dessert){
        recommendedMeals.setValue(dessert);
    }

    public void setMeal(DetialMealDTO meal) {
        mealLiveData.setValue(meal);
    }

    public boolean hasData() {
        return mealLiveData.getValue() != null;
    }

    public DetialMealDTO getCachedMeal() {
        return mealLiveData.getValue();
    }
    public  List<Meal>getCachedRecommendedMeadls(){
        return  recommendedMeals.getValue();
    }
    public void clearCache() {
        mealLiveData.setValue(null);
        recommendedMeals.setValue(null);
    }
}