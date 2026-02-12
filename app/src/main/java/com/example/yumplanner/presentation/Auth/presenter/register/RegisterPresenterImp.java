package com.example.yumplanner.presentation.Auth.presenter.register;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.yumplanner.data.auth.datasource.remote.AuthCallback;
import com.example.yumplanner.data.auth.AuthRepo;
import com.example.yumplanner.data.auth.model.User;
import com.example.yumplanner.presentation.Auth.view.register.RegisterView;
import com.example.yumplanner.utiles.sharedpreferance.PrefsHelper;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPresenterImp  implements  RegisterPresenter{
    private AuthRepo authRepo ;
    private RegisterView registerView;
    Application application;

    MaterialButton guestBtn;
    Activity activity;
    public RegisterPresenterImp(Application application, RegisterView registerView,Activity activity){
        this.authRepo=new AuthRepo(application);
        this.registerView=registerView;
        this.application=application;
        this.activity=activity;
    }
    @Override
    public void login() {
        registerView.navigateToLogin();

    }
    @Override
    public void register(String email, String password,String name) {
        registerView.showLoading();
        authRepo.signUp(email, password, new AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                registerView.hideLoading();
                authRepo.checkUser(new User(user.getUid(), name, user.getEmail()));


                registerView.showSnackBarSuccess("Register succeed");
                registerView.navigateToLogin();
             }
            @Override
            public void onError(String message) {
                registerView.hideLoading();
                registerView.showSnackBarFailure(message);

            }
        });
    }

    @Override
    public void regeisterByGoogle() {
        authRepo.googleRegister( activity , new AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                registerView.hideLoading();
                if (user.getDisplayName()==null||user.getDisplayName().isEmpty()) {
                    String nameFromEmail = user.getEmail().substring(0, user.getEmail().indexOf("@"));
                    Log.d("NAME", nameFromEmail);
                    authRepo.checkUser(new User(user.getUid(), nameFromEmail, user.getEmail()));
                }
                else {
                    authRepo.checkUser(new User(user.getUid(), user.getDisplayName(), user.getEmail()));
                }
                registerView.showSnackBarSuccess("Register succeed");
                registerView.navigateToHome();

            }

            @Override
            public void onError(String message) {
                registerView.showSnackBarFailure(message);

            }
        });

    }

    @Override
    public void enterAsGuest() {
        PrefsHelper.getInstance(application).setIsGuest(true);
        registerView.navigateToHome();



    }


}
