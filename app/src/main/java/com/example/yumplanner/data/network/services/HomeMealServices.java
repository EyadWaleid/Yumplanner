package com.example.yumplanner.data.network.services;


import com.example.yumplanner.data.network.response.MealResponse;
import com.example.yumplanner.data.network.response.DetailMealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeMealServices {
    @GET("random.php")
    Observable<DetailMealResponse> getRandommeal();
    @GET("filter.php?c=Dessert")
    Observable<MealResponse> getrecommended();
    @GET("lookup.php")
    Observable<DetailMealResponse> getDetailById(@Query("i") String id);


    


}
