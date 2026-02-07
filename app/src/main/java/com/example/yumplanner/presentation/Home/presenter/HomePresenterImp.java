package com.example.yumplanner.presentation.Home.presenter;

import com.example.yumplanner.data.dataSource.home.HomeRepo;
import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.presentation.Home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter {

    private HomeRepo homeRepo;
    private HomeView homeView;
    private final CompositeDisposable disposables;
    DetialMealDTO detailMeal;
    public HomePresenterImp(HomeView homeView) {
        this.homeRepo = new HomeRepo();
        this.homeView = homeView;
        this.disposables = new CompositeDisposable();
    }
    @Override
    public void getRandomData() {
        homeView.showLoading();
        fetchFromApi();

    }
    @Override
    public void reachDetails() {
        homeView.showLoading();

        homeView.toDetial(detailMeal);
        homeView.hideLoading();
    }
    @Override
    public void toDessertDetail(String id) {
        homeView.toDessertDetial(id);

    }
    private void fetchFromApi() {
        Disposable disposable = homeRepo.getRandomProducts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        detailMeal -> {
                           this.detailMeal=detailMeal;

                            homeView.setSpecialMeal(detailMeal.getMealName(),detailMeal.getImgeUrl());
                        },
                        throwable -> {
                            homeView.hideLoading();
                            homeView.showError();
                        }
                );
        Disposable disposableRecommended=homeRepo.getReocommendedDessert().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                meals -> {

                    homeView.setDessert(meals);
                    homeView.hideLoading();
                    homeView.showBackground();
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