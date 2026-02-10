package com.example.yumplanner.data;

import com.example.yumplanner.data.home.datasource.remote.HomeRemoteDataSource;
import com.example.yumplanner.data.search.remote.SearchRemoteDataSource;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.home.model.dto.AreaDTO;
import com.example.yumplanner.data.home.model.dto.CategoryDto;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.Search.model.SearchableItems;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class MainRepo {
    HomeRemoteDataSource homeRemoteDataSource;
    SearchRemoteDataSource searchRemoteDataSource;
    public MainRepo(){

        homeRemoteDataSource=new HomeRemoteDataSource();
        searchRemoteDataSource=new SearchRemoteDataSource();
    }
    public Observable<DetialMeal> getRandomProducts(){
      return   homeRemoteDataSource.getRandomMeal().map(meal -> {

                         return new DetialMeal(meal.getIdMeal(),meal.getStrMeal(),meal.getStrCategory(),meal.getStrArea(),meal.getSteps(),meal.getStrMealThumb(),meal.getIngredientList());
     });
    }
    public Observable<List<Meal>>getReocommendedDessert(){
        return homeRemoteDataSource.getRecommendedDessert();
    }
    public  Observable<DetialMeal>getDetialById(String id){
        return  homeRemoteDataSource.getDessertDetial(id).map(meal -> {
            return new DetialMeal(meal.getIdMeal(),meal.getStrMeal(),meal.getStrCategory(),meal.getStrArea(),meal.getSteps(),meal.getStrMealThumb(),meal.getIngredientList());

        });
    }
    public  Observable<List<SearchableItems>> getIngredient(){
        return searchRemoteDataSource.getIngredientData().flatMapIterable(ingredients -> ingredients).map(ingredient -> {
            return new SearchableItems( ingredient.getStrIngredient(),ingredient.getStrThumb());
        }).toList().toObservable();
    }
    public  Observable<List<CategoryDto>> getCategoryList(){
        return  searchRemoteDataSource.getCategoryData().map(
                map->{
                    if (map == null || map.get(0) == null) {
                        throw new RuntimeException();
                    }return map;

                }
        );
    }
    public  Observable<List<AreaDTO>> getArea(){
        return  searchRemoteDataSource.getArea();
    }
    public Observable<List<Meal>>getMeals(Map<String,String> map){
        return searchRemoteDataSource.getSearchDetail(map);
    }
    public Observable<List<Meal>>getSearchMeal(String query){
        return  searchRemoteDataSource.getMealSearched(query);
    }

}
