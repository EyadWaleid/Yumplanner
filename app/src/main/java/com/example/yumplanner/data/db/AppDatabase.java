package com.example.yumplanner.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yumplanner.data.auth.datasource.dao.UserDAO;
import com.example.yumplanner.data.auth.model.User;
import com.example.yumplanner.data.home.model.DetialMeal;

@Database(version = 1,entities = {User.class})
public abstract class AppDatabase extends RoomDatabase {
    public  abstract UserDAO userDAO();
    public  abstract DetialMeal mealDAO();
    public  static AppDatabase INSTANCE;
    public  static  synchronized AppDatabase getINSTANCE(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,AppDatabase.class,"yumplanDB").build();
        }
        return  INSTANCE;
    }
}
