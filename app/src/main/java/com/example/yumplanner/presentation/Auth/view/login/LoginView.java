package com.example.yumplanner.presentation.Auth.view.login;

public interface LoginView {
    void  showLoading();
    void hideLoading();
    void showSnackBarSuccess(String message);
    void showSnackBarFailure(String message );
    void navigateToHome();
    void navigateToRegister();
    void navigateToForgetPassword();
}
