package com.example.yumplanner.presentation.plan.presenter;

import android.content.Context;
import android.util.Log;

import com.example.yumplanner.data.detail.DetailRepo;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.model.Entity.PlannedMealEntity;
import com.example.yumplanner.presentation.details.view.DetialView;
import com.example.yumplanner.presentation.plan.view.CalenderView;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenterImp implements PlanPresenter {
    CalenderView calenderView;
    DetailRepo detailRepo;
    String date;

    PlannedMealEntity plannedMealEntity;
    private CompositeDisposable compositeDisposable;
    String userId= FirebaseAuth.getInstance().getUid();
    public PlanPresenterImp(CalenderView calenderView, Context context) {
        this.calenderView = calenderView;
        detailRepo = new DetailRepo(context);
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void getMeal(String date) {
        this.date=date;
        compositeDisposable.add(
                detailRepo.getMealData(date,userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                detailMealEntity -> {
                                    this.plannedMealEntity = detailMealEntity;
                                    calenderView.setText(detailMealEntity.getMealName());
                                    calenderView.setImage(detailMealEntity.getImageUrl());
                                    calenderView.hideNoData();
                                    calenderView.showData();
                                },
                                error -> {
                                    calenderView.showNoData();
                                    calenderView.hideData();
                                },
                                () -> {
                                    calenderView.showNoData();
                                    calenderView.hideData();
                                }
                        )
        );
    }
    public void onDestroy() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void deleteBtn( ) {
        compositeDisposable.add(  detailRepo.deletePlanMeal(date,FirebaseAuth.getInstance().getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        () ->{ calenderView.showSuccessSnackBar();
                            calenderView.hideData();
                            calenderView.showNoData();
                            plannedMealEntity = null;}
                ));
    }
    @Override
    public void goToDetails() {
        calenderView.toDetail(new DetialMeal(plannedMealEntity.getMealName(), plannedMealEntity.getMealId(), plannedMealEntity.getCategory(), plannedMealEntity.getArea(), plannedMealEntity.getInstructions(),plannedMealEntity.getImageUrl(),plannedMealEntity.getMealIngredients()));
    }

    @Override
    public void onNetworkConnected() {
        calenderView.adddDeleteICon();


    }

    @Override
    public void onNetworkDisconnected() {
        calenderView.removeDeletICon();

    }
}