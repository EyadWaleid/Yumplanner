package com.example.yumplanner.presentation.details.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.yumplanner.data.MainRepo;
import com.example.yumplanner.data.detail.DetailRepo;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.model.Entity.FavMealEntity;
import com.example.yumplanner.data.model.Entity.PlannedMealEntity;
import com.example.yumplanner.presentation.details.view.DetialView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetialPresenterImp implements DetialPresenter {

    MainRepo mainRepo;
    DetailRepo detailRepo;
    DetialMeal detialMeal;
    String dateSelected = "";
    DetialView view;
    String uid;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DetialPresenterImp(DetialView detialView, Context context) {
        mainRepo = new MainRepo(context);
        view = detialView;
        detailRepo = new DetailRepo(context);
        uid = FirebaseAuth.getInstance().getUid();
    }

    @Override
    public void getData(DetialMeal meal) {
        detialMeal = meal;
        view.showLoader();
        view.setData(meal.getImgeUrl(), meal.getMealName());
        view.setIngredients(meal.getIngredients());
        view.setSteps(meal.getInstructions());
        view.hideLoader();
        view.showView();
        compositeDisposable.add(
                detailRepo.checkIsFav(uid, detialMeal.getMealId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(isFav -> {
                            if (isFav > 0) {
                                view.fillIcon();
                            } else {
                                view.unFillIcon();
                            }
                        })
                        .subscribe(
                                integer -> {
                                    view.setData(meal.getImgeUrl(), meal.getMealName());
                                    view.setIngredients(meal.getIngredients());
                                    view.setSteps(meal.getInstructions());
                                    view.showView();
                                },
                                error -> {
                                  view.unFillIcon();
                                }
                        )
        );
    }

    @Override
    public void getDataById(String id) {
        view.showLoader();
        compositeDisposable.add(
                mainRepo.getDetialById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(meal -> {
                            detialMeal = meal;
                            view.setData(meal.getImgeUrl(), meal.getMealName());
                            view.setIngredients(meal.getIngredients());
                            view.setSteps(meal.getInstructions());
                        }).observeOn(AndroidSchedulers.mainThread())
                        .flatMap(detialMeal1 ->
                                detailRepo.checkIsFav(uid, detialMeal1.getMealId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                        .map(integer -> {
                                            if (integer > 0) {
                                                view.fillIcon();
                                            } else {
                                                view.unFillIcon();
                                            }
                                            return detialMeal1;
                                        })
                                        .toObservable()
                        )
                        .subscribe(
                                result -> {
                                    view.hideLoader();
                                    view.showView();
                                },
                                error -> {
                                    view.hideLoader();
                                    Log.d("FAV", error.toString());

                                }
                        )
        );

    }

    @Override
    public void addCalender() {
        view.showCalender();
    }

    @Override
    public void onDateSelected(String date) {
        this.dateSelected = date;
    }

    @Override
    public void OnSaveVMeal(Context context) {
        if (dateSelected.isEmpty()) {
            dateSelected = view.setDate();
        }

        Disposable d = detailRepo.checkMeal(dateSelected, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        aBoolean -> {
                            if (aBoolean) {
                                view.showFailureSnackbar();
                            } else {
                                Glide.with(context)
                                        .asBitmap()
                                        .load(detialMeal.getImgeUrl())
                                        .into(new CustomTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                String imagePath = saveBitmapToInternal(resource, context);

                                                String finalImagePath = (imagePath != null && !imagePath.isEmpty())
                                                        ? imagePath
                                                        : detialMeal.getImgeUrl();

                                                PlannedMealEntity entity = new PlannedMealEntity(
                                                        detialMeal.getMealId(),
                                                        detialMeal.getMealName(),
                                                        detialMeal.getCategory(),
                                                        detialMeal.getArea(),
                                                        detialMeal.getInstructions(),
                                                        dateSelected,
                                                        finalImagePath,
                                                        detialMeal.getIngredients(),
                                                        uid
                                                );

                                                compositeDisposable.add(detailRepo.insertMeal(entity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                                         () -> {
                                                             view.hideCalender();
                                                             view.showSuccessSnackbar();
                                                         }
                                                ));

                                            }
                                            @Override
                                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                            }

                                            @Override
                                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                                Toast.makeText(context, "Image download failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        },
                        error -> {
                        }
                );
        compositeDisposable.add(d);
    }

    @Override
    public void favMeal() {
        Disposable d = detailRepo.checkIsFav(uid, detialMeal.getMealId())
                .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(
                        isFav -> {
                            if (isFav > 0) {

                                compositeDisposable.add(
                                        detailRepo.deleteFavMeal(detialMeal.getMealId(), uid).subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> view.unFillIcon())
                                );

                            } else {
                                FavMealEntity favMeal = new FavMealEntity(
                                        detialMeal.getMealId(),
                                        detialMeal.getMealName(),
                                        detialMeal.getCategory(),
                                        detialMeal.getArea(),
                                        detialMeal.getInstructions(),
                                        detialMeal.getImgeUrl(),
                                        detialMeal.getIngredients(),
                                        uid
                                );
                                detailRepo.addFavMeal(favMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        () -> {
                                            view.fillIcon();

                                        }
                                );



                            }
                        },
                        error -> {
                            Log.e("FAV_MEAL", "Error toggling favorite: " + error.getMessage());
                        }
                );
        compositeDisposable.add(d);
    }

    @Override
    public void cancelCalender() {
        view.hideCalender();
    }

    private String saveBitmapToInternal(Bitmap bitmap, Context context) {
        String name = detialMeal.getMealName();
        String fileName = name.length() >= 3 ? name.substring(0, 3) + ".png" : name + ".png";
        File directory = context.getFilesDir();
        File imageFile = new File(directory, fileName);

        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            Log.e("SAVE_IMAGE", "Failed to save image: " + e.getMessage());
            return "";
        }
    }

    public void clear() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}