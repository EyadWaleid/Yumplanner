package com.example.yumplanner.utiles;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarHelper {

    public enum Type {
        SUCCESS,
        ERROR,
        WARNING
    }

    // Activity version
    public static void show(Activity activity, String message, Type type) {
        int color;
        switch (type) {
            case SUCCESS:
                color = Color.parseColor("#4CAF50"); // green
                break;
            case ERROR:
                color = Color.parseColor("#F44336"); // red
                break;
            case WARNING:
                color = Color.parseColor("#FF9800"); // orange
                break;
            default:
                color = Color.DKGRAY;
        }

        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_SHORT
        );

        snackbar.setBackgroundTint(color);
        TextView textView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(Typeface.DEFAULT_BOLD);

        snackbar.show();
    }

    // Fragment version
    public static void show(View rootView, String message, Type type) {
        int color;
        switch (type) {
            case SUCCESS:
                color = Color.parseColor("#4CAF50");
                break;
            case ERROR:
                color = Color.parseColor("#F44336");
                break;
            case WARNING:
                color = Color.parseColor("#FF9800");
                break;
            default:
                color = Color.DKGRAY;
        }

        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(color);
        TextView textView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        snackbar.show();
    }
}

