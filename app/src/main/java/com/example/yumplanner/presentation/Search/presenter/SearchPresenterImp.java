package com.example.yumplanner.presentation.Search.presenter;

import android.content.Context;
import android.util.Log;

import com.example.yumplanner.data.MainRepo;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.Search.model.SearchableItems;
import com.example.yumplanner.presentation.Search.view.SearchView;
import com.example.yumplanner.data.model.mapping.AreaCountryMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {
    MainRepo mainRepo;
    SearchView searchView;
    private final CompositeDisposable disposables;
    List<Meal>meals = new ArrayList<>();
    List<SearchableItems> searchableItemsList =new ArrayList<>();
    public SearchPresenterImp(SearchView view, Context context) {
        this.disposables = new CompositeDisposable();
        mainRepo = new MainRepo(context);
        searchView=view;
    }
    @Override
    public void getCategoryList() {
        searchView.hideData();
        searchView.viewLoader();
        Disposable d = mainRepo.getCategoryList().
                subscribeOn(Schedulers.io()).delay(1000,TimeUnit.MILLISECONDS).flatMapIterable(categories -> categories)
                .map(category -> new SearchableItems(category.getStrCategory(), category.getStrCategoryThumb()))
                .toList()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(categories -> {
                            searchableItemsList =categories;
                            searchView.setCategoryData(categories,"c");
                            searchView.hideLoader();
                            searchView.showData();
                        },
                        onError->{
                    searchView.hideLoader();
                            Log.d("ErrorInData",onError.getMessage());
                        }
                );
        disposables.add(d);
    }
    @Override
    public void getIngredientList() {
        searchView.hideData();
        searchView.viewLoader();
        Disposable d = mainRepo.getIngredient().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                searchableItems -> {
                    searchableItemsList =searchableItems;
                    searchView.setCategoryData(searchableItems,"i");
                    searchView.hideLoader();
                    searchView.showData();
                },
                onError ->{
                }
        );
        disposables.add(d);
    }

    @Override
    public void getAreaList()  {
        searchView.hideData();
        searchView.viewLoader();
        Disposable d = mainRepo.getArea().subscribeOn(Schedulers.io()).flatMapIterable(areas -> areas)
                .map(area -> {
                    String code = AreaCountryMapper.AREA_TO_COUNTRY_CODE.get(area.getStrArea());
                    if (code == null) code = "un";
                    return new SearchableItems(
                            area.getStrArea(),
                            "https://flagcdn.com/w320/" + code + ".png"
                    );
                }).toList()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(areas -> {
                            searchableItemsList=areas;
                            searchView.setAreaData(areas,"a");
                            searchView.hideLoader();
                           searchView.showData();
                 },
                        onError->{
                              searchView.hideData();
                            Log.d("ErrorInData",onError.getMessage());
                        }
                );
        disposables.add(d);
    }

    @Override
    public void searchData(Observable<String> searchQuery) {
       searchView.hideData();
        searchView.viewLoader();
        Disposable d = searchQuery
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(query -> {
                    if (query.isEmpty()) {
                        return Observable.just(searchableItemsList);
                    } else {
                        return Observable.fromIterable(searchableItemsList)
                                .filter(user -> user.getName().toLowerCase().contains(query.toLowerCase()))
                                .toList()
                                .toObservable();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        results -> {
                            searchView.hideLoader();
                            searchView.showData();
                            searchView.addSearchedData(results);
                            },
                        throwable -> {

                        }
                );

        disposables.add(d);
    }
    @Override
    public void toSearchResult(String name,String type) {
        searchView.toResultDetials(name,type);
    }

public  void getMeals(){
    searchView.addSearchMeal(meals, "s");
}
/* @Override
 public void searchByMealName(Observable<String> searchQuery) {
        searchView.hideData();
        Disposable d = searchQuery
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(query -> {
                    if (query.isEmpty()) {
                        return Observable.just(meals);
                    } else {
                        return mainRepo.getSearchMeal(query).subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        results -> {
                            searchView.clearData();
                                meals = results;
                                searchView.addSearchMeal(results, "s");
                            searchView.hideLoader();
                            searchView.showData();
                        },
                        throwable -> {
                            searchView.hideLoader();
                        },
                             ()->{
                            searchView.hideLoader();
                             }

                );


        disposables.add(d);
    }*/
    public  void onDestroy() {
        disposables.clear();
    }
}