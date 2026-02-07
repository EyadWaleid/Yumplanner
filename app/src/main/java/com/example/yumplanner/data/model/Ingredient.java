package com.example.yumplanner.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    private String name;
    private String measure;

    private String imageUrl;

    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
        if (name != null && !name.isEmpty()) {
            this.imageUrl = "https://www.themealdb.com/images/ingredients/" + name.replace(" ", "%20") + ".png";
        } else {
            this.imageUrl = null;
        }
    }

    protected Ingredient(Parcel in) {
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

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getName() { return name; }
    public String getMeasure() { return measure; }
    public String getImageUrl() { return imageUrl; }
}
