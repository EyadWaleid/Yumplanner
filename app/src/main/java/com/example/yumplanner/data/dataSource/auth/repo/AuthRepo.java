package com.example.yumplanner.data.dataSource.auth.repo;

import android.app.Activity;
import android.app.Application;

import com.airbnb.lottie.animation.content.Content;
import com.example.yumplanner.data.dataSource.auth.local.UserDataCallback;
import com.example.yumplanner.data.dataSource.auth.local.UserDataSourceLocal;
import com.example.yumplanner.data.dataSource.auth.remote.AuthCallback;
import com.example.yumplanner.data.dataSource.auth.remote.AuthenticatDataSource;
import com.example.yumplanner.data.dataSource.auth.remote.ForgetPaswordCallback;
import com.example.yumplanner.data.model.User;

public class AuthRepo {
    private AuthenticatDataSource authenticatDataSource;
    private UserDataSourceLocal userDataSourceLocal;

    public  AuthRepo(Application application){

        this.authenticatDataSource= new AuthenticatDataSource();
        this.userDataSourceLocal=new UserDataSourceLocal(application);

    }
    public  void login(String email, String pasword, AuthCallback authCallback){
         authenticatDataSource.login(email,pasword,authCallback);

    }
    public  void signUp(String email,String password ,AuthCallback authCallback){
         authenticatDataSource.signUp(email,password,authCallback);
    }
    public  void googleLogin(Activity activity, AuthCallback authCallback){
        authenticatDataSource.loginWithGoogle(activity,authCallback);

    }
    public  void forgetPassword(String email, ForgetPaswordCallback authCallback){
        authenticatDataSource.resetPassword(email,authCallback);
    }
    public void checkUser(User user ){
        userDataSourceLocal.addUserIfNotExists(user);
    }
    public  void  googleRegister(Activity activity,AuthCallback authCallback){
        authenticatDataSource.RegisterWithGoogle(activity,authCallback);
    }
    public void signOut(){
        authenticatDataSource.signOut();
    }
}
