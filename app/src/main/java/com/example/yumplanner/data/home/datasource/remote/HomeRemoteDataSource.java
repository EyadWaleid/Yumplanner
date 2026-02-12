package com.example.yumplanner.data.home.datasource.remote;



import com.example.yumplanner.data.home.model.dto.DetailMealModelDto;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.data.network.init.AppNetwork;
import com.example.yumplanner.data.network.response.MealResponse;
import com.example.yumplanner.data.network.services.HomeMealServices;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class HomeRemoteDataSource {
    private HomeMealServices homeMealServices;
    public HomeRemoteDataSource  (){this.homeMealServices =new AppNetwork().getRandomServices();}
    public Observable<DetailMealModelDto> getRandomMeal() {
        return homeMealServices.getRandommeal()
                .flatMap(detailMeal -> {
                    DetailMealModelDto meal = detailMeal.getMeals().get(0);

                    if ("Dessert".equals(meal.getStrCategory())) {
                        return getRandomMeal();
                    } else {
                        return Observable.just(meal);
                    }
                });
    }
    public  Observable<List<Meal>> getRecommendedDessert(){
        return  homeMealServices.getrecommended().map(dessertResponse -> { if (dessertResponse.getMeal().size() > 5) {
            return dessertResponse.getMeal().subList(0, 5);
        }
            return dessertResponse.getMeal(); });
    }
    public Observable<DetailMealModelDto> getDessertDetial(String id ){
        return  homeMealServices.getDetailById(id).map(detailMealResponse -> detailMealResponse.getMeals().get(0)) ;
    }

    }


