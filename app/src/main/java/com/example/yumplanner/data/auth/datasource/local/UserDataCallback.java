package com.example.yumplanner.data.auth.datasource.local;

public interface UserDataCallback {
    void onUserAdded();
    void onUserAlreadyExists();
    void onError(String message);
}
