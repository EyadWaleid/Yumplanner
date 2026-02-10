package com.example.yumplanner.presentation.details.presenter;

import android.util.Log;

import com.example.yumplanner.data.MainRepo;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.presentation.details.view.DetialView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetialPresenterImp  implements DetialPresenter{
    MainRepo mainRepo;
    DetialView view;
    public  DetialPresenterImp(DetialView detialView){
        mainRepo =new MainRepo();
        view=detialView;
    }
    @Override
    public void getData(DetialMeal meal) {

        view.showLoader();
        view.setData(meal.getImgeUrl(), meal.getMealName());
        view.setIngredients(meal.getIngredients());


        view.setSteps(meal.getInstructions());
        view.hideLoader();
        view.showView();
    }

    @Override
    public void getDataById(String id) {
        Log.d("detailId","the id arrived->"+id);
        view.showLoader();
        mainRepo.getDetialById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                meal -> {
                    view.setData(meal.getImgeUrl(), meal.getMealName());
                    view.setIngredients(meal.getIngredients());


                    view.setSteps(meal.getInstructions());
                    view.hideLoader();
                    view.showView();
                },
                onError->{
                    view.hideLoader();
                    view.showError();

                }
        );

    }
    @Override
    public void addCalender() {
        view.showCalender();

    }
}
