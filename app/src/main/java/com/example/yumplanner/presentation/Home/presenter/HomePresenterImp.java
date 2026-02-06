package com.example.yumplanner.presentation.Home.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.yumplanner.data.dataSource.home.HomeRepo;
import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;
import com.example.yumplanner.data.model.Meal;
import com.example.yumplanner.presentation.Home.view.HomeView;
import com.example.yumplanner.presentation.Home.view.HomeViewModel;
import com.example.yumplanner.presentation.details.view.DetialActivity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter {

    private HomeRepo homeRepo;
    private HomeView homeView;
    private HomeViewModel viewModel;
    private CompositeDisposable disposables;
    DetialMealDTO detailMeal;
    public HomePresenterImp(HomeView homeView, HomeViewModel viewModel) {
        this.homeRepo = new HomeRepo();
        this.homeView = homeView;
        this.viewModel = viewModel;
        this.disposables = new CompositeDisposable();
    }

    @Override
    public void getRandomData() {
        homeView.showLoading();
        if (viewModel.hasData()) {
               detailMeal = viewModel.getCachedMeal();
            List<Meal> dessertMeal=viewModel.getCachedRecommendedMeadls();
            Log.d("cache","data is cached");
            homeView.setSpecialMeal(detailMeal.getMealName(),detailMeal.getImgeUrl());
            homeView.setDessert(dessertMeal);
            homeView.hideLoading();
            return;
        }
        fetchFromApi();

    }

    @Override
    public void reachDetails() {
        homeView.showLoading();

        homeView.toDetial(detailMeal);
        homeView.hideLoading();
    }


    public void refreshData() {
        viewModel.clearCache();
        fetchFromApi();
    }

    private void fetchFromApi() {
        homeView.showLoading();

        Disposable disposable = homeRepo.getRandomProducts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        detailMeal -> {
                           this.detailMeal=detailMeal;

                            viewModel.setMeal(detailMeal);
                            homeView.setSpecialMeal(detailMeal.getMealName(),detailMeal.getImgeUrl());
                        },
                        throwable -> {
                            homeView.hideLoading();
                            homeView.showError();
                        }
                );
        Disposable disposableRecommended=homeRepo.getReocommendedDessert().subscribe(
                meals -> {

                    viewModel.setDessert(meals);
                    homeView.setDessert(meals);
                    homeView.hideLoading();
                },
                throwable -> {
                    homeView.hideLoading();
                    homeView.showError();
                }
        );
        disposables.add(disposable);
        disposables.add(disposableRecommended);
    }

    public void onDestroy() {
        disposables.clear();

    }
}