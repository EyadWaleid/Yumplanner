package com.example.yumplanner.data.detail;

import android.content.Context;
import android.util.Log;

import com.example.yumplanner.data.detail.local.FavLocalDataSource;
import com.example.yumplanner.data.detail.local.PlanMealDetailLocalDataSource;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.model.Entity.FavMealEntity;
import com.example.yumplanner.data.model.Entity.PlannedMealEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailRepo {
    PlanMealDetailLocalDataSource planMealDetailLocalDataSource;
    FavLocalDataSource favLocalDataSource;
  public  DetailRepo(Context context){
        planMealDetailLocalDataSource =new PlanMealDetailLocalDataSource(context);
        favLocalDataSource=new FavLocalDataSource(context);
    }
    public Completable insertMeal(PlannedMealEntity plannedMealEntity){
      return  planMealDetailLocalDataSource.insertDetailMeal(plannedMealEntity);
    }
    public Single<Boolean> checkMeal(String date,String id ){
      return planMealDetailLocalDataSource.checkPlannedMeal(date, id );
    }
    public Maybe<PlannedMealEntity>getMealData(String date ,String id){

        return  planMealDetailLocalDataSource.getMealdetails(date,id)
              .switchIfEmpty(
                      Maybe.error(new Exception())
              );
    }
    public Single<Integer> checkIsFav(String userId , String mealId){
      return   favLocalDataSource.isFav(userId,mealId);
    }
    public Completable addFavMeal(FavMealEntity favMealEntity){
         return favLocalDataSource.addFavMeal(favMealEntity);
    }
    public  Completable deleteFavMeal(String mealId,String id){
   return     favLocalDataSource.deleteFavMeal(mealId,id);
    }
    public  Completable deletePlanMeal(String date,String userId){
      return  planMealDetailLocalDataSource.deleteData(date,userId);
    }
    public Observable<List<DetialMeal>> loadAllFav(String userId){
        return favLocalDataSource.getAllFavMeal(userId)
                .map(entities -> {
                    List<DetialMeal> result = new ArrayList<>();
                    for (FavMealEntity entity : entities) {
                        result.add(new DetialMeal(
                                entity.getMealName(),
                                entity.getMealId(),
                                entity.getCategory(),
                                entity.getArea(),
                                entity.getInstructions(),
                                entity.getImageUrl(),
                                entity.getMealIngredients()
                        ));
                    }
                    Log.d("Repo", "Mapped meals: " + result.size());
                    return result;
                });
    }
}
