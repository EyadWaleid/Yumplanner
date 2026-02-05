package com.example.yumplanner.presentation.Auth.presenter.forgetpassword;

import android.app.Activity;

import com.example.yumplanner.data.dataSource.auth.remote.AuthCallback;
import com.example.yumplanner.data.dataSource.auth.remote.ForgetPaswordCallback;
import com.example.yumplanner.data.dataSource.auth.repo.AuthRepo;
import com.example.yumplanner.presentation.Auth.view.forgetpassword.ForgetPasswordPage;
import com.example.yumplanner.presentation.Auth.view.forgetpassword.ForgetpasswordView;
import com.google.firebase.auth.FirebaseUser;

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
                toLogin();

            }

            @Override
            public void onError(String message) {

            }
        });


    }

    @Override
    public void toLogin() {
        forgetpasswordView.navigateToLogin();;

    }
}
