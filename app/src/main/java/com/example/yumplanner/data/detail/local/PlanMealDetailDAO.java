package com.example.yumplanner.data.detail.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yumplanner.data.model.Entity.PlannedMealEntity;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PlanMealDetailDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Completable insertMeals(PlannedMealEntity plannedMealEntity);
    @Query("SELECT COUNT(*) FROM PlannedMealEntity WHERE date = :date and userId = :id")
    Single<Integer> checkSavedData(String date, String id);
    @Query("SELECT * FROM PlannedMealEntity WHERE date = :date and userId = :id")
    Maybe<PlannedMealEntity>getPlannedData(String date,String id);
    @Query("DELETE FROM PlannedMealEntity WHERE date = :date and userId = :userId")
    Completable deleteMeal(String date ,String userId);

}
