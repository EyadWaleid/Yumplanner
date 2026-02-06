package com.example.yumplanner.presentation.details.presenter;

import com.example.yumplanner.data.dataSource.home.HomeRepo;
import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.presentation.details.view.DetialView;

public class DetialPresenterImp  implements DetialPresenter{
    HomeRepo homeRepo;
    DetialView view;
    public  DetialPresenterImp(DetialView detialView){
        homeRepo=new HomeRepo();
        view=detialView;
    }

    @Override
    public void getData(DetialMealDTO meal) {

        view.showLoader();
        view.setData(meal.getImgeUrl(), meal.getMealName());
        view.setIngredients(meal.getIngredients());


        view.setSteps(meal.getInstructions());
        view.hideLoader();
        view.showView();
    }

    @Override
    public void getDataById(String id) {

    }

    @Override
    public void addCalender() {
        view.showCalender();

    }
}
