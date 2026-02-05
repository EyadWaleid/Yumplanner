package com.example.yumplanner.presentation.Auth.view.register;

public interface RegisterView {
    void  showLoading();
    void hideLoading();
    void showSnackBarSuccess(String message);
    void showSnackBarFailure(String message );
    void navigateToLogin();
}
