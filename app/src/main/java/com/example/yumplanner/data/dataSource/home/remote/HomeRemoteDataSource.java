package com.example.yumplanner.data.dataSource.home.remote;


import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;
import com.example.yumplanner.data.model.Ingredient;
import com.example.yumplanner.data.model.Meal;
import com.example.yumplanner.data.network.init.AppNetwork;
import com.example.yumplanner.data.network.services.HomeMealServices;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class HomeRemoteDataSource {
    private HomeMealServices homeMealServices;
    public HomeRemoteDataSource  (){this.homeMealServices =new AppNetwork().getRandomServices();}
    public Observable<DetailMeal> getRandomMeal() {
        return homeMealServices.getRandommeal()
                .flatMap(detailMeal -> {
                    DetailMeal meal = detailMeal.getMeals().get(0);

                    if ("Dessert".equals(meal.getStrCategory())) {
                        return getRandomMeal();
                    } else {
                        return Observable.just(meal);
                    }
                });
    }
    public  Observable<List<Meal>> getRecommendedDessert(){
        return  homeMealServices.getrecommended().map(dessertResponse -> { if (dessertResponse.getDesert().size() > 5) {
            return dessertResponse.getDesert().subList(0, 5);
        }
            return dessertResponse.getDesert(); });
    }

    public Observable<DetailMeal> getDessertDetial(String id ){
        return  homeMealServices.getDetailById(id).map(detailMealResponse -> detailMealResponse.getMeals().get(0)) ;
    }

    }


