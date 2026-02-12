package com.example.yumplanner.data.network.services;

import com.example.yumplanner.data.network.response.AreaResponse;
import com.example.yumplanner.data.network.response.CategoryResponse;
import com.example.yumplanner.data.network.response.IngredientResponse;
import com.example.yumplanner.data.network.response.MealResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableError;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SearchService {
    @GET("categories.php")
    Observable<CategoryResponse> getCategory();
    @GET("list.php?a=list")
    Observable<AreaResponse> getAreas();
    @GET("list.php?i=list")
    Observable<IngredientResponse>getIngrediant();
    @GET("filter.php")
    Observable<MealResponse> getSearched(@QueryMap Map<String, String> params);
    @GET("search.php")
    Observable<MealResponse> getMealSearch(@Query("s") String query);
}
