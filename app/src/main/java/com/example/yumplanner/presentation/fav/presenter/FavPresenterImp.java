package com.example.yumplanner.presentation.fav.presenter;

import android.content.Context;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yumplanner.data.detail.DetailRepo;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.presentation.fav.view.FavView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenterImp implements  FavPresenter  {
    FavView favView ;
    String userId;
    DetailRepo detailRepo;
    List<DetialMeal> detialMeal;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public FavPresenterImp(FavView favView,Context context){
        detailRepo=new DetailRepo(context);
        this.favView=favView;
        userId=FirebaseAuth.getInstance().getUid();
        Log.d("ID",userId.toString());
    }

  @Override
    public void loadFavData() {
        favView.showLoader();

      compositeDisposable.add(

      detailRepo.loadAllFav(userId.toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                  detialMeal -> {
                      this.detialMeal=detialMeal;
                      if(this.detialMeal.isEmpty()){
                          favView.showNoData();
                          favView.hideLoader();
                      }
                      else {
                      favView.setData(detialMeal);
                      favView.hideLoader();
                      favView.hidNoData();
                      favView.showBackGround();}
                  },
                  throwable -> {
                      favView.hideLoader();
                      favView.showNoData();
                  }

          )
        );
    }

    @Override
    public void deleteFavData(DetialMeal detialMeal) {
            compositeDisposable.add(
                    detailRepo.deleteFavMeal(detialMeal.getMealId(),userId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                               favView.showFavSnackBar("Deleted successfully");

                            })
            );
    }
    @Override
    public void goToDetails(DetialMeal detialMeal) {
        favView.goToDetails(detialMeal);
    }

    @Override
    public void onNetworkConnected() {
        favView.updateNetworkState(true);

    }

    @Override
    public void onNetworkDisconnected() {
        favView.updateNetworkState(false);


    }
}
