package com.example.yumplanner.data.dataSource.auth.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yumplanner.data.dataSource.auth.remote.AuthCallback;
import com.example.yumplanner.data.db.AppDatabase;
import com.example.yumplanner.data.db.UserDAO;
import com.example.yumplanner.data.model.User;

public class UserDataSourceLocal {
    private UserDAO userDAO;
    public  UserDataSourceLocal(Context context){

        this.userDAO= AppDatabase.getINSTANCE(context).userDAO();
    }

    public void addUserIfNotExists(User user) {
     new Thread(() -> {
         try {
             // Check if user exists
             boolean existingUser = userDAO.checkUser(user.getId());

             if (existingUser ) {
                    return;
             } else {
                 // User doesn't exist - add them
                 userDAO.insertUser(user);
             }
         } catch (Exception e) {
           e.printStackTrace();
         }

     }).start();
    }}




