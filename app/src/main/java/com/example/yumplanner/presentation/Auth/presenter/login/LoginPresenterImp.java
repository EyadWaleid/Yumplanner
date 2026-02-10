package com.example.yumplanner.presentation.Auth.presenter.login;

import android.app.Activity;

import com.example.yumplanner.data.auth.datasource.remote.AuthCallback;
import com.example.yumplanner.data.auth.AuthRepo;
import com.example.yumplanner.data.auth.model.User;
import com.example.yumplanner.presentation.Auth.view.login.LoginView;
import com.example.yumplanner.utiles.PrefsHelper;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImp  implements  LoginPresenter{
    private AuthRepo authRepo ;
    private LoginView loginView;
    private  Activity activity;
   public LoginPresenterImp(Activity application, LoginView loginView){
        this.authRepo=new AuthRepo(application.getApplication());
        this.loginView=loginView;
       this.activity=application;
    }
    @Override
    public void login(String email, String password) {
       loginView.showLoading();
            authRepo.login(email, password, new AuthCallback() {


                @Override
                public void onSuccess(FirebaseUser user) {
                    loginView.hideLoading();
                    loginView.showSnackBarSuccess("Login succeed");
                    loginView.navigateToHome();
                    PrefsHelper.getInstance(activity).setLoggedIn(true);
                  authRepo.checkUser(new User(user.getUid(), user.getDisplayName(), user.getEmail()));
                }

                @Override
                public void onError(String message) {
                    loginView.hideLoading();
                    loginView.showSnackBarFailure(message);
                }
            });
    }
    @Override
    public  void loginWithGoogle(){
        authRepo.googleLogin(activity, new AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                authRepo.checkUser(new User(user.getUid(), user.getDisplayName(), user.getEmail()));

                loginView.showSnackBarSuccess("Login succeed");
                loginView.navigateToHome();
                PrefsHelper.getInstance(activity).setLoggedIn(true);
            }

            @Override
            public void onError(String message) {
                loginView.hideLoading();
                loginView.showSnackBarFailure(message);
            }
        });

    }
    @Override
    public void register() {
       loginView.navigateToRegister();
    }
    @Override
    public void forgetPassword() {
       loginView.navigateToForgetPassword();
    }

}
