package com.example.yumplanner.presentation.Auth.presenter.register;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.yumplanner.data.dataSource.auth.remote.AuthCallback;
import com.example.yumplanner.data.dataSource.auth.repo.AuthRepo;
import com.example.yumplanner.data.model.User;
import com.example.yumplanner.presentation.Auth.view.login.LoginView;
import com.example.yumplanner.presentation.Auth.view.register.RegisterView;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPresenterImp  implements  RegisterPresenter{
    private AuthRepo authRepo ;
    private RegisterView registerView;
    public RegisterPresenterImp(Application activity, RegisterView registerView){
        this.authRepo=new AuthRepo(activity);
        this.registerView=registerView;
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


}
