package com.example.yumplanner.presentation.Auth.presenter.forgetpassword;

import android.app.Activity;

import com.example.yumplanner.data.auth.datasource.remote.ForgetPaswordCallback;
import com.example.yumplanner.data.auth.AuthRepo;
import com.example.yumplanner.presentation.Auth.view.forgetpassword.ForgetpasswordView;

public class ForgetpasswordPresenterImp  implements  ForgetPasswordPresenter{
    private  AuthRepo authRepo;
    private ForgetpasswordView forgetpasswordView;
    public  ForgetpasswordPresenterImp(Activity activity, ForgetpasswordView forgetpasswordView){
        this.authRepo=new AuthRepo(activity.getApplication());
        this.forgetpasswordView=forgetpasswordView;
    }
    @Override
    public void forgetPassword(String email) {
        authRepo.forgetPassword(email, new ForgetPaswordCallback() {
            @Override
            public void OnSuccessForgetPassword() {
                forgetpasswordView.showSuccess();
                toLogin();
            }
            @Override
            public void onError(String message) {
                   forgetpasswordView.showFailure(message);
            }
        });
    }

    @Override
    public void toLogin() {
        forgetpasswordView.navigateToLogin();;

    }
}
