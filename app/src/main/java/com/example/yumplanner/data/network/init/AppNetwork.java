package com.example.yumplanner.data.network.init;

import com.example.yumplanner.data.network.services.HomeMealServices;
import com.example.yumplanner.data.network.services.SearchService;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppNetwork {
    private HomeMealServices homeMealServices;
    private SearchService searchService;
    private Retrofit retrofit;

    public AppNetwork() {
        retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
    public HomeMealServices getRandomServices(){
        if(homeMealServices ==null){
            homeMealServices = retrofit.create(HomeMealServices.class);
        }
        return homeMealServices;
    }
    public SearchService getSearchService(){
        if(searchService==null){
            searchService=retrofit.create(SearchService.class);

        }
        return  searchService;
    }

}
