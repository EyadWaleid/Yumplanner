package com.example.yumplanner.data.detail.remote;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.yumplanner.data.auth.model.User;
import com.example.yumplanner.data.home.model.DetialMeal;

@Dao
public interface DetailDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertMeals(DetialMeal detialMeal);


}
