package com.example.yumplanner.data.dataSource.auth.remote;

import com.google.firebase.auth.FirebaseUser;

public interface AuthCallback {
    void onSuccess(FirebaseUser user);
    void onError(String message );

}
