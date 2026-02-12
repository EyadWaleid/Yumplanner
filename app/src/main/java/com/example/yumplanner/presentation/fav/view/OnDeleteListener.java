package com.example.yumplanner.presentation.fav.view;

import com.example.yumplanner.data.home.model.DetialMeal;
import com.google.android.material.button.MaterialButton;

public interface OnDeleteListener {
   void removeMealFromFav(DetialMeal detialMeal);
   void goTODetial(DetialMeal detialMeal);

}
