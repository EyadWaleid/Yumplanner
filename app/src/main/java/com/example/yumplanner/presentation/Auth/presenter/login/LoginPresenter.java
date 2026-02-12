package com.example.yumplanner.presentation.Auth.presenter.login;

import android.app.Activity;

public interface LoginPresenter {
    void login(String email , String password );
    void loginWithGoogle();
    void register();
    void forgetPassword();
}
