package com.example.yumplanner.data.db;

import androidx.room.TypeConverter;

import com.example.yumplanner.data.home.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Convertor {
    private static Gson gson = new Gson();


    @TypeConverter
    public  static String fromIngredientList(List<Ingredient> ingredients) {
        return gson.toJson(ingredients);
    }

    @TypeConverter
    public  static  List<Ingredient> toIngredientList(String data) {
        if (data == null) return null;
        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return gson.fromJson(data, listType);}

    @TypeConverter
    public static String fromStringList(List<String> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<String> toStringList(String data) {
        if (data == null) return null;
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(data, listType);
    }
}
