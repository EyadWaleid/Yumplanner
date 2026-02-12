package com.example.yumplanner.data.detail.local;

import android.content.Context;
import android.util.Log;

import com.example.yumplanner.data.db.AppDatabase;
import com.example.yumplanner.data.model.Entity.PlannedMealEntity;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class PlanMealDetailLocalDataSource {
    PlanMealDetailDAO planMealDetailDAO;

    public PlanMealDetailLocalDataSource(Context context){
        planMealDetailDAO = AppDatabase.getINSTANCE(context).mealDAO();
    }
    public Completable insertDetailMeal(PlannedMealEntity plannedMealEntity){

           return       planMealDetailDAO.insertMeals(plannedMealEntity);
    }
    public Single<Boolean> checkPlannedMeal(String date, String id){

       return planMealDetailDAO.checkSavedData(date,id)
                .map(integer -> {
                    if(integer ==0){
                        return false;
                    }
                    else {
                        return  true;
                    }
                }) ;



    }
    public  Maybe<PlannedMealEntity>getMealdetails(String date,String id){

        return  planMealDetailDAO.getPlannedData(date,id);
    }
    public  Completable deleteData(String date,String userId){
        return  planMealDetailDAO.deleteMeal(date,userId);
    };
}
