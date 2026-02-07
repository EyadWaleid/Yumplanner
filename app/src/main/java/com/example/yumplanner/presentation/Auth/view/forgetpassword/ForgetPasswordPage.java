package com.example.yumplanner.presentation.Auth.view.forgetpassword;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Auth.presenter.forgetpassword.ForgetPasswordPresenter;
import com.example.yumplanner.presentation.Auth.presenter.forgetpassword.ForgetpasswordPresenterImp;
import com.example.yumplanner.presentation.Auth.view.register.RegisterPageDirections;
import com.example.yumplanner.utiles.SnackbarHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class ForgetPasswordPage extends Fragment implements ForgetpasswordView {
   MaterialButton sendingCode;
   TextInputEditText editText;
   Button loginBtn;
   ForgetPasswordPresenter forgetPasswordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_forget_password_page, container, false);
         sendingCode=view.findViewById(R.id.restoreAccountBtn);
         editText=view.findViewById(R.id.emailInputFroget);
         loginBtn=view.findViewById(R.id.toLoginBtnfromForget);

     return  view;
     }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forgetPasswordPresenter= new ForgetpasswordPresenterImp(requireActivity(),this);
        sendingCode.setOnClickListener(v -> {
            forgetPasswordPresenter.forgetPassword(editText.getText().toString());
        });
        loginBtn.setOnClickListener(v -> forgetPasswordPresenter.toLogin());
    }
    @Override
    public void navigateToLogin() {
        NavController controller= Navigation.findNavController(getView());
        controller.navigate(ForgetPasswordPageDirections.actionForgetPasswordPageToLoginPage());
    }

    @Override
    public void showLoading() {


    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess() {
        SnackbarHelper.show(getView(),"Link send to your email", SnackbarHelper.Type.SUCCESS);

    }

    @Override
    public void showFailure(String message) {
        SnackbarHelper.show(getView(),message, SnackbarHelper.Type.ERROR);

    }
}