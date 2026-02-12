package com.example.yumplanner.presentation.Auth.view.forgetpassword;

public interface ForgetpasswordView {
    void navigateToLogin();
    void showLoading();
    void hideLoading();
    void showSuccess();
    void showFailure(String error);
}
