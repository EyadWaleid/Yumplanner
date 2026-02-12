package com.example.yumplanner.data.home.model;


import android.os.Parcel;
import android.os.Parcelable;

public class MealIngredient implements Parcelable {
    private String name;
    private String measure;

    private String imageUrl;

    public MealIngredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
        if (name != null && !name.isEmpty()) {
            this.imageUrl = "https://www.themealdb.com/images/ingredients/" + name.replace(" ", "%20") + ".png";
        } else {
            this.imageUrl = null;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    protected MealIngredient(Parcel in) {
        name = in.readString();
        measure = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(measure);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MealIngredient> CREATOR = new Creator<MealIngredient>() {
        @Override
        public MealIngredient createFromParcel(Parcel in) {
            return new MealIngredient(in);
        }

        @Override
        public MealIngredient[] newArray(int size) {
            return new MealIngredient[size];
        }
    };

    public String getName() { return name; }
    public String getMeasure() { return measure; }
    public String getImageUrl() { return imageUrl; }
}
