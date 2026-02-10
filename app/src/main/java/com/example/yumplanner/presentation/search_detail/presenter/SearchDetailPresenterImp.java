package com.example.yumplanner.presentation.search_detail.presenter;

import android.util.Log;

import com.example.yumplanner.data.MainRepo;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.search_detail.view.SearchDetialView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchDetailPresenterImp implements SearchDetailPresenter {
    MainRepo repo;
    SearchDetialView searchDetailview;
    List<Meal> mealList;
    private CompositeDisposable disposables = new CompositeDisposable();

    public SearchDetailPresenterImp(SearchDetialView searchDetail){
        this.searchDetailview = searchDetail;
        repo = new MainRepo();
    }

    @Override
    public void searchData(Observable<String> searchQuery) {
        Disposable d = searchQuery
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(query -> {

                    if (query.isEmpty()) {
                        return Observable.just(mealList);
                    } else {
                        return Observable.fromIterable(mealList)
                                .filter(user -> user.getMealName().toLowerCase().contains(query))
                                .toList()
                                .toObservable();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        results -> searchDetailview.setItemData(results),
                        throwable -> {

                        }
                );

        disposables.add(d);
    }
    @Override
    public void getMealsData(Map<String, String> map) {
        Disposable disposable = repo.getMeals(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                                Log.d("success", meals.get(0).getMealId());
                                mealList=meals;
                            searchDetailview.setItemData(meals);
                          },
                        throwable -> Log.d("error", "Failed to load meals",throwable)
                );

        disposables.add(disposable);
    }

    @Override
    public void toDetailScreen(String id) {
         searchDetailview.goDetials(id);
    }

    public void onDestroy() {
        disposables.clear();
    }
}

