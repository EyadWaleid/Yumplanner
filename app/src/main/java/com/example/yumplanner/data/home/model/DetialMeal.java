package com.example.yumplanner.data.home.model;



import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class DetialMeal implements Parcelable {
    private  String mealName;
    private String mealId;
    private  String category;
    private String area;
    private  List<String> instructions;
    private  String imgeUrl;
    private  boolean isFav;

    private List<MealIngredient> mealIngredients;

    public DetialMeal(String mealName, String mealId, String category, String area, List<String> instructions, String imgeUrl, List<MealIngredient> mealIngredients) {
        this.mealName = mealName;
        this.mealId = mealId;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.imgeUrl = imgeUrl;
        this.mealIngredients = mealIngredients;
    }



    public List<MealIngredient> getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(List<MealIngredient> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    protected DetialMeal(Parcel in) {
        mealName = in.readString();
        mealId = in.readString();
        category = in.readString();
        area = in.readString();
        instructions = in.createStringArrayList();
        imgeUrl = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isFav=in.readBoolean();
        }
        mealIngredients = in.createTypedArrayList(MealIngredient.CREATOR);
    }

    public static final Creator<DetialMeal> CREATOR = new Creator<DetialMeal>() {
        @Override
        public DetialMeal createFromParcel(Parcel in) {
            return new DetialMeal(in);
        }

        @Override
        public DetialMeal[] newArray(int size) {
            return new DetialMeal[size];
        }
    };

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
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

    public String getImgeUrl() {
        return imgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public List<MealIngredient> getIngredients() {
        return mealIngredients;
    }

    public void setIngredients(List<MealIngredient> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mealName);
        dest.writeString(mealId);
        dest.writeString(category);
        dest.writeString(area);
        dest.writeStringList(instructions);
        dest.writeString(imgeUrl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(isFav);
        }
        dest.writeTypedList(mealIngredients);
    }


}