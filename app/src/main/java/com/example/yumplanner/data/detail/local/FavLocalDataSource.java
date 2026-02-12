package com.example.yumplanner.data.detail.local;

import android.content.Context;

import com.example.yumplanner.data.db.AppDatabase;
import com.example.yumplanner.data.model.Entity.FavMealEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class FavLocalDataSource {
    FavMealDao favMealDao;
    public FavLocalDataSource(Context contxt){
        favMealDao = AppDatabase.getINSTANCE(contxt).favMealDao();
    }
    public Single<Integer> isFav(String userId, String mealId){
        return  favMealDao.isFavorite( userId,mealId );
    }
    public  Observable<List<FavMealEntity>>getAllFavMeal(String id ){
        return  favMealDao.getAllFavById(id);
    }
    public  Completable addFavMeal(FavMealEntity favMealEntity){
         return   favMealDao.insertFavMeal(favMealEntity);

     }
     public  Completable deleteFavMeal(String mealId,String userId){


                  return   favMealDao.deleteById(mealId,userId);


     }

}
