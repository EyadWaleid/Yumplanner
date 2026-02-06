package com.example.yumplanner.data.dto;



import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.yumplanner.data.model.Ingredient;

import java.util.List;

public class DetialMealDTO implements Parcelable {
    private  String mealName;
    private String mealId;
    private  String category;
    private String area;
    private  List<String> instructions;
    private  String imgeUrl;
    private List<Ingredient>ingredients;



    public DetialMealDTO(String idMeal, String strMeal, String strCategory, String strArea, List<String>strInstructions, String strMealThumb,List<Ingredient>ingredients) {
        this.mealId=idMeal;
        this.mealName=strMeal;
        this.category=strCategory;
        this.area=strArea;
        this.instructions =strInstructions;
        this.imgeUrl=strMealThumb;
        this.ingredients=ingredients;
    }

    protected DetialMealDTO(Parcel in) {
        mealName = in.readString();
        mealId = in.readString();
        category = in.readString();
        area = in.readString();
        instructions = in.createStringArrayList();
        imgeUrl = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    }

    public static final Creator<DetialMealDTO> CREATOR = new Creator<DetialMealDTO>() {
        @Override
        public DetialMealDTO createFromParcel(Parcel in) {
            return new DetialMealDTO(in);
        }

        @Override
        public DetialMealDTO[] newArray(int size) {
            return new DetialMealDTO[size];
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
        dest.writeTypedList(ingredients);
    }


}