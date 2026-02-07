package com.example.yumplanner.presentation.Auth.view.login;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yumplanner.presentation.Auth.presenter.login.LoginPresenter;
import com.example.yumplanner.presentation.Auth.presenter.login.LoginPresenterImp;
import com.example.yumplanner.presentation.Home.view.HomeActivity;
import com.example.yumplanner.R;
import com.example.yumplanner.utiles.SnackbarHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends Fragment implements LoginView {
    TextInputLayout emailLayout;
    View progressBar;
    TextInputLayout passwordLayout;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;
    Button forgetPasswordBtn;
    Button signUpBtn;
    TextView emailInputError;
    TextView passwordInputError;
    MaterialButton loginBtn;
    MaterialButton googlebtn;
    LoginPresenter loginPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_login_page, container, false);

    emailLayout=view.findViewById(R.id.emailLayout);
    emailInput=view.findViewById(R.id.emailInput);
    passwordLayout=view.findViewById(R.id.passwordLayout);
    passwordInput=view.findViewById(R.id.passwordInput);
    loginBtn=view.findViewById(R.id.loginButton);
    forgetPasswordBtn=view.findViewById(R.id.forget_passowrd_btn);
    signUpBtn=view.findViewById(R.id.signup_btn);
    progressBar=view.findViewById(R.id.loadingOverlay);
    googlebtn=view.findViewById(R.id.googleBtn);
    emailInputError=view.findViewById(R.id.emailInputError);
    passwordInputError=view.findViewById(R.id.passwordInputError);


         return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        loginPresenter=new LoginPresenterImp(requireActivity(),this);
        super.onViewCreated(view, savedInstanceState);

        loginBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if(email.isEmpty()){
                emailInputError.setVisibility(TextView.VISIBLE);
                emailInputError.setText("Enter your  email");
                emailLayout.setError("Enter your  email");
                return;
            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailInputError.setVisibility(TextView.VISIBLE);
                emailInputError.setText("Enter a valid email");
                emailLayout.setError("Enter your  email");
                return;
            } else {
                emailInputError.setVisibility(TextView.INVISIBLE);
                emailLayout.setError(null);            }

            if(password.isEmpty()){
                passwordLayout.setBoxStrokeColor(ContextCompat.getColor(requireContext(), R.color.stroke_colour));
                passwordInputError.setVisibility(TextView.VISIBLE);
                emailLayout.setError(null);
                return;
            }

            if(password.length() < 6){
                passwordLayout.setError("null");
                passwordInputError.setVisibility(TextView.VISIBLE);
                passwordInputError.setText("Password must be at least 6 characters");
                return;
            } else {
                passwordInputError.setVisibility(TextView.INVISIBLE);
                passwordLayout.setError(null);
            }

            loginPresenter.login(email, password);
        });
        forgetPasswordBtn.setOnClickListener(v -> {
            loginPresenter.forgetPassword();

        });
        signUpBtn.setOnClickListener(v -> {
             loginPresenter.register();

        });
        googlebtn.setOnClickListener(v -> {
            loginPresenter.loginWithGoogle();
        });


    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(ProgressBar.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);

    }

    @Override
    public void showSnackBarSuccess(String message) {
        SnackbarHelper.show(getView(),message, SnackbarHelper.Type.SUCCESS);

    }

    @Override
    public void showSnackBarFailure(String message) {
        SnackbarHelper.show(getView(),message, SnackbarHelper.Type.ERROR);

    }

    @Override
    public void navigateToHome() {
                    Intent intent = new Intent(requireContext(), HomeActivity.class);
            startActivity(intent);
            requireActivity().finish();
    }

    @Override
    public void navigateToRegister() {
        NavController controller= Navigation.findNavController(getView());
        controller.navigate(LoginPageDirections.actionLoginPageToRegisterPage());

    }

    @Override
    public void navigateToForgetPassword() {
        NavController controller= Navigation.findNavController(getView());
        controller.navigate(LoginPageDirections.actionLoginPageToForgetPasswordPage());
    }
}