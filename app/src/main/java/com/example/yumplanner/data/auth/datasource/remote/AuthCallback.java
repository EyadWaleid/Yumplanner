package com.example.yumplanner.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseUser;

public interface AuthCallback {
    void onSuccess(FirebaseUser user);
    void onError(String message );

}
