package com.example.yumplanner.data.detail;

import android.content.Context;

import com.example.yumplanner.data.detail.local.FavLocalDataSource;
import com.example.yumplanner.data.detail.local.PlanMealDetailLocalDataSource;
import com.example.yumplanner.data.model.Entity.FavMealEntity;
import com.example.yumplanner.data.model.Entity.PlannedMealEntity;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

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
}
