package com.example.yumplanner.presentation.Search.view;
import android.text.TextWatcher;

import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.Search.model.SearchableItems;
import java.util.List;
public interface  SearchView {
    void setCategoryData(List<SearchableItems> searchableItemsList,String type);
    void setAreaData(List<SearchableItems> searchableItemsList,String type);
    void addSearchedData(List<SearchableItems>searchableItemsList);
    void viewLoader();
    void addSearchMeal(List<Meal> meals,String type);
    void hideLoader();
    void showData();
    void hideData();
    void clearData();
    void setTextWatcher(TextWatcher textWatcher);
   void toResultDetials(String name, String type);


}
