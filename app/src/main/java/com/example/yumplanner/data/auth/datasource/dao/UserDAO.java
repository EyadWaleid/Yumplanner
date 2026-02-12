package com.example.yumplanner.data.auth.datasource.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yumplanner.data.auth.model.User;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(User user);
    @Query("Select name from users where id= :id")
    boolean checkUser(String id);
    @Query("Select name from users where id= :id")
    Single<String> getUserName(String id);


}
