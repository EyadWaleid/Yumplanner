package com.example.yumplanner.utiles.sharedpreferance;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsHelper {

    private static final String PREFS_NAME = "yumplanner_prefs";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private  static  final String KEY_IS_GUEST_IN="is_guest_in";

    private static PrefsHelper instance;
    private SharedPreferences sharedPreferences;

    // Private constructor
    private PrefsHelper(Context context) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Singleton getter
    public static synchronized PrefsHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PrefsHelper(context);
        }
        return instance;
    }

    public void setLoggedIn(boolean loggedIn) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply();
    }
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    public  void setIsGuest(boolean isGuest){
        sharedPreferences.edit().putBoolean(KEY_IS_GUEST_IN,isGuest);
    }
    public  boolean isGuestisIn(){
        return  sharedPreferences.getBoolean(KEY_IS_GUEST_IN,false);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
