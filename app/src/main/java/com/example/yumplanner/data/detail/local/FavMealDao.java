package com.example.yumplanner.data.detail.local;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.yumplanner.data.model.Entity.FavMealEntity;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
//Competable is a type of JavaRx using to make or do a method without waiting any return like insert or delete
//Single is used to provide just one time listen to the database or one time listen
@Dao
public interface FavMealDao {
 //Competable for insert values
 @Insert(onConflict = OnConflictStrategy.ABORT)
 Completable insertFavMeal(FavMealEntity favMealEntity);
 @Query("SELECT * FROM FavMealEntity WHERE  userId = :userId ")
 Observable<List<FavMealEntity>> getAllFavById(String userId);
 @Query("SELECT count(*) FROM FavMealEntity WHERE  userId= :userId and mealId = :id")
 //Single for one time listen to the database if there is fav bar or not
 //can not use observable cause if u try to click you will go in loop of removing and inserting data until your phone crash
 Single<Integer> isFavorite(String userId, String id);
 @Query("DELETE FROM FavMealEntity WHERE mealId = :id and userId = :userId")
 //Competable for delete
 Completable deleteById(String id,String userId);

}
