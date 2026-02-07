package com.example.yumplanner.data.dataSource.home;

import android.util.Log;

import com.example.yumplanner.data.dataSource.home.remote.HomeRemoteDataSource;
import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;
import com.example.yumplanner.data.model.Ingredient;
import com.example.yumplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeRepo {
    HomeRemoteDataSource homeRemoteDataSource;
    public  HomeRepo(){
        homeRemoteDataSource=new HomeRemoteDataSource();
    }
    public Observable<DetialMealDTO> getRandomProducts(){
      return   homeRemoteDataSource.getRandomMeal().map(meal -> {

                         return new DetialMealDTO(meal.getIdMeal(),meal.getStrMeal(),meal.getStrCategory(),meal.getStrArea(),meal.getSteps(),meal.getStrMealThumb(),meal.getIngredientList());
     });
    }
    public Observable<List<Meal>>getReocommendedDessert(){
        return homeRemoteDataSource.getRecommendedDessert();
    }
    public  Observable<DetialMealDTO>getDetialById(String id){
        return  homeRemoteDataSource.getDessertDetial(id).map(meal -> {
            return new DetialMealDTO(meal.getIdMeal(),meal.getStrMeal(),meal.getStrCategory(),meal.getStrArea(),meal.getSteps(),meal.getStrMealThumb(),meal.getIngredientList());

        });
    }


}
