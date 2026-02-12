package com.example.yumplanner.data.db;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.yumplanner.data.auth.datasource.dao.UserDAO;
import com.example.yumplanner.data.auth.model.User;
import com.example.yumplanner.data.detail.local.PlanMealDetailDAO;
import com.example.yumplanner.data.detail.local.FavMealDao;
import com.example.yumplanner.data.model.Entity.FavMealEntity;
import com.example.yumplanner.data.model.Entity.PlannedMealEntity;
@Database(version = 1,entities = {User.class, PlannedMealEntity.class , FavMealEntity.class})
@TypeConverters(Convertor.class)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract UserDAO userDAO();
    public  abstract FavMealDao favMealDao();
    public  abstract PlanMealDetailDAO mealDAO();
    public  static AppDatabase INSTANCE;
    public  static  synchronized AppDatabase getINSTANCE(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,AppDatabase.class,"yumplanDB").addMigrations().build();
        }
        return  INSTANCE;
    }

}
