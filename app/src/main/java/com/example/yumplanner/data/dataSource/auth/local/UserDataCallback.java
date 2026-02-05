package com.example.yumplanner.data.dataSource.auth.local;

public interface UserDataCallback {
    void onUserAdded();
    void onUserAlreadyExists();
    void onError(String message);
}
