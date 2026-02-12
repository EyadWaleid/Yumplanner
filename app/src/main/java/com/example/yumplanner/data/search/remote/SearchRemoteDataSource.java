package com.example.yumplanner.data.search.remote;

import android.util.Log;

import com.example.yumplanner.data.home.model.dto.AreaDTO;
import com.example.yumplanner.data.home.model.dto.CategoryDto;
import com.example.yumplanner.data.home.model.dto.IngredientDto;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.data.network.init.AppNetwork;
import com.example.yumplanner.data.network.services.SearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class SearchRemoteDataSource {
    SearchService searchService;

    public SearchRemoteDataSource() {
        this.searchService = new AppNetwork().getSearchService();
    }

    public Observable<List<CategoryDto>> getCategoryData() {
      return   searchService.getCategory().map(categoryResponse -> categoryResponse.getCategories()).onErrorReturn(throwable -> {
          List<CategoryDto>categories = new ArrayList<>();

          categories.add(new CategoryDto("1","hi","hi","hi"));
          return  categories ;
      });
    }
   public Observable<List<IngredientDto>> getIngredientData() {
        return   searchService.getIngrediant().map(ingredientResponse -> ingredientResponse.getIngredients());
    }
    public Observable<List<AreaDTO>> getArea(){
        return  searchService.getAreas().map( areaResponse -> areaResponse.getAreas());
    }

     public Observable<List<Meal>> getSearchDetail(Map<String, String> searchVariable) {
    Log.d("SearchRemote", "Searching with params: " + searchVariable);

    return searchService.getSearched(searchVariable)
            .doOnNext(response -> Log.d("SearchRemote", "Response: " + response))
            .doOnError(error -> Log.e("SearchRemote", "Error occurred", error))
            .map(mealResponse -> {
                if (mealResponse == null) {
                    Log.e("SearchRemote", "Response is null!");
                    return new ArrayList<>();
                }
                if (mealResponse.getMeal() == null) {
                    Log.e("SearchRemote", "Meals list is null!");
                    return new ArrayList<>();
                }
                Log.d("SearchRemote", "Found " + mealResponse.getMeal().size() + " meals");
                return mealResponse.getMeal();
            });
}

     public  Observable<List<Meal>>getMealSearched(String query){
        return  searchService.getMealSearch(query).map(mealResponse -> mealResponse.getMeal());
     }
}
