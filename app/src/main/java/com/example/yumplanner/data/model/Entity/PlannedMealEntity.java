package com.example.yumplanner.data.model.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.yumplanner.data.db.Convertor;
import com.example.yumplanner.data.home.model.MealIngredient;

import java.util.List;

@Entity(tableName = "PlannedMealEntity", primaryKeys = {"date", "userId"})
@TypeConverters({Convertor.class})
public class PlannedMealEntity {
    private String mealId;
    @ColumnInfo(name="mealName")
    private  String mealName;
    @ColumnInfo(name="category")

    private  String category;
    @ColumnInfo(name ="area")
    private String area;
    @ColumnInfo(name="instructions")

    private List<String> instructions;
    @ColumnInfo(name = "date")
    @NonNull
    private String date;
    @ColumnInfo(name = "imageUrl")
    private  String imageUrl;
    @ColumnInfo(name="ingredients")
    private List<MealIngredient> mealIngredients;
    @ColumnInfo(name="userId")
    @NonNull

    private String userId;

    public List<MealIngredient> getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(List<MealIngredient> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }

    public PlannedMealEntity(@NonNull String mealId, String mealName, String category, String area, List<String> instructions, String date, String imageUrl, List<MealIngredient> mealIngredients, @NonNull String userId) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.date = date;
        this.imageUrl = imageUrl;
        this.mealIngredients = mealIngredients;
        this.userId = userId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }
}
