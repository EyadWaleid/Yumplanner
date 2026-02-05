package com.example.yumplanner.presentation.Auth.view.register;

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
import android.widget.ProgressBar;

import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Auth.presenter.register.RegisterPresenter;
import com.example.yumplanner.presentation.Auth.presenter.register.RegisterPresenterImp;
import com.example.yumplanner.utiles.SnackbarHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterPage extends Fragment implements RegisterView {
    TextInputLayout emailLayout;
    TextInputLayout passwordLayout;
    TextInputLayout passwordRepeatLayout;
    TextInputEditText nameInput;
    TextInputLayout nameLayout;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;
    TextInputEditText passwordRepeatInput;
    MaterialButton signUpBtn;
    Button loginBtn;
    MaterialButton googlebtn;
    View progressBar;
    RegisterPresenter registerPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_register_page, container, false);
         emailLayout=view.findViewById(R.id.registerEmailLayout);
         emailInput=view.findViewById(R.id.registerEmailInput);
         passwordInput=view.findViewById(R.id.passwordRegistrationInput);
         passwordLayout=view.findViewById(R.id.passwordRegistrationLayout);
         signUpBtn=view.findViewById(R.id.registerBtn);
         loginBtn=view.findViewById(R.id.toLoginBtn);
         nameInput=view.findViewById(R.id.nameInput);
         nameLayout=view.findViewById(R.id.nameLayout);
         passwordRepeatInput=view.findViewById(R.id.passwordRegistrationRepatInput);
         passwordRepeatLayout=view.findViewById(R.id.passwordRegistrationRepatLayout);
         progressBar=view.findViewById(R.id.registerProgress);
     return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         registerPresenter=new RegisterPresenterImp(requireActivity().getApplication(),this);
        signUpBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if(email.isEmpty()){
                emailLayout.setError("Enter your email");
                return;
            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailLayout.setError("Enter a valid email");
                return;
            } else {
                emailLayout.setError(null);
            }

            if(password.isEmpty()){
                passwordLayout.setError("Enter your password");
                return;
            }

            if(password.length() < 6){
                passwordLayout.setError("Password must be at least 6 characters");
                return;
            } else {
                passwordLayout.setError(null);
            }
            registerPresenter.register(emailInput.getText().toString(),passwordInput.getText().toString());

        });
        loginBtn.setOnClickListener(v->{

          registerPresenter.login();


        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(ProgressBar.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(ProgressBar.GONE);
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
    public void navigateToLogin() {
        NavController controller= Navigation.findNavController(getView());
        controller.navigate(RegisterPageDirections.actionRegisterPageToLoginPage());

    }
}