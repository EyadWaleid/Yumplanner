package com.example.yumplanner.presentation.Home.presenter;

import android.util.Pair;

import com.example.yumplanner.data.MainRepo;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.presentation.Home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter  {

    private MainRepo mainRepo;
    private HomeView homeView;
    private final CompositeDisposable disposables;
    DetialMeal detailMeal;
    public HomePresenterImp(HomeView homeView) {
        this.mainRepo = new MainRepo();
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
        Disposable disposable = Observable.zip(
                        mainRepo.getRandomProducts().subscribeOn(Schedulers.io()),
                        mainRepo.getReocommendedDessert().subscribeOn(Schedulers.io()),
                        (detailMeal, meals) -> new Pair<>(detailMeal, meals)
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            this.detailMeal = result.first;
                            homeView.setSpecialMeal(result.first.getMealName(), result.first.getImgeUrl());
                            homeView.setDessert(result.second);
                            homeView.hideLoading();
                            homeView.showBackground();
                        },
                        throwable -> {
                            homeView.hideLoading();
                            homeView.showError();
                        }
                );

        disposables.add(disposable);

    }
    public void onDestroy() {
        disposables.clear();
    }

    @Override
    public void onNetworkConnected() {
        homeView.hidNetworkError();
        homeView.showBackground();

    }

    @Override
    public void onNetworkDisconnected() {
        homeView.hideBackground();
        homeView.showNetworkError();

    }
}