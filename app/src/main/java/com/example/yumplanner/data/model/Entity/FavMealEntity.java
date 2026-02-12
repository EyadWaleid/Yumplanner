package com.example.yumplanner.data.model.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.yumplanner.data.db.Convertor;
import com.example.yumplanner.data.home.model.MealIngredient;

import java.util.List;
@Entity(tableName = "FavMealEntity", primaryKeys = {"mealId", "userId"})
@TypeConverters({Convertor.class})
public class FavMealEntity {
    @NonNull
    private String mealId;
    @ColumnInfo(name="mealName")
    private  String mealName;
    @ColumnInfo(name="category")
    private  String category;
    @ColumnInfo(name ="area")
    private String area;
    @ColumnInfo(name="instructions")
    private List<String> instructions;
    @ColumnInfo(name = "imageUrl")
    private  String imageUrl;
    @ColumnInfo(name="ingredients")
    private List<MealIngredient> mealIngredients;
    @NonNull
    @ColumnInfo(name="userId")
    private String userId;

    public List<MealIngredient> getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(List<MealIngredient> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<MealIngredient> getIngredients() {
        return mealIngredients;
    }

    public void setIngredients(List<MealIngredient> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FavMealEntity(String mealId, String mealName, String category, String area, List<String> instructions, String imageUrl, List<MealIngredient> mealIngredients, String userId) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.mealIngredients = mealIngredients;
        this.userId = userId;
    }
}
