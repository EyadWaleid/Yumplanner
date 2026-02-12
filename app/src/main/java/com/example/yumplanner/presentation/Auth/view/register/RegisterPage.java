package com.example.yumplanner.presentation.Auth.view.register;

import android.content.Intent;
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
import android.widget.TextView;

import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Auth.presenter.register.RegisterPresenter;
import com.example.yumplanner.presentation.Auth.presenter.register.RegisterPresenterImp;
import com.example.yumplanner.presentation.Home.view.HomeActivity;
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
    TextView nameErrorInput;
    TextView emailErrorInput;
    TextView passwordErrorInput;
    TextView confirmPasswordErrorInput;
    MaterialButton googlebtn;
    View progressBar;
    RegisterPresenter registerPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_page, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerPresenter = new RegisterPresenterImp(requireActivity().getApplication(), this,this.requireActivity());
        signUpBtn.setOnClickListener(v -> {

            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String repeatPassword = passwordRepeatInput.getText().toString().trim();

            if (name.isEmpty()) {
                nameLayout.setError("Enter your name");
                nameErrorInput.setText("Enter your name");
                nameErrorInput.setVisibility(View.VISIBLE);
                return;
            } else {
                nameLayout.setError(null);
                nameErrorInput.setVisibility(View.INVISIBLE);
            }


            if (email.isEmpty()) {
                emailLayout.setError("Enter your email");
                emailErrorInput.setText("Enter your email");
                emailErrorInput.setVisibility(View.VISIBLE);
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.setError("Enter a valid email");
                emailErrorInput.setText("Enter a valid email");
                emailErrorInput.setVisibility(View.VISIBLE);
                return;
            } else {
                emailLayout.setError(null);
                emailErrorInput.setVisibility(View.INVISIBLE);
            }

            if (password.isEmpty()) {
                passwordLayout.setError("Enter your password");
                passwordErrorInput.setText("Enter your password");
                passwordErrorInput.setVisibility(View.VISIBLE);
                return;
            }

            if (password.length() < 6) {
                passwordLayout.setError("Password must be at least 6 characters");
                passwordErrorInput.setText("Password must be at least 6 characters");
                passwordErrorInput.setVisibility(View.VISIBLE);
                return;
            } else {
                passwordLayout.setError(null);
                passwordErrorInput.setVisibility(View.INVISIBLE);
            }

            if (!repeatPassword.equals(password)) {
                passwordRepeatLayout.setError("Passwords do not match");
                confirmPasswordErrorInput.setText("Passwords do not match");
                confirmPasswordErrorInput.setVisibility(View.VISIBLE);
                return;
            } else {
                passwordRepeatLayout.setError(null);
                confirmPasswordErrorInput.setVisibility(View.INVISIBLE);
            }

            registerPresenter.register(email, password, name);
        });
        loginBtn.setOnClickListener(v -> {
            registerPresenter.login();
        });
        googlebtn.setOnClickListener(v -> {
            registerPresenter.regeisterByGoogle();
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
        SnackbarHelper.show(getView(), message, SnackbarHelper.Type.SUCCESS);
    }
    @Override
    public void showSnackBarFailure(String message) {
        SnackbarHelper.show(getView(), message, SnackbarHelper.Type.ERROR);
    }

    public void initViews(View view) {
        emailLayout = view.findViewById(R.id.registerEmailLayout);
        emailInput = view.findViewById(R.id.registerEmailInput);
        passwordInput = view.findViewById(R.id.passwordRegistrationInput);
        passwordLayout = view.findViewById(R.id.passwordRegistrationLayout);
        signUpBtn = view.findViewById(R.id.registerBtn);
        loginBtn = view.findViewById(R.id.toLoginBtn);
        nameInput = view.findViewById(R.id.nameInput);
        nameLayout = view.findViewById(R.id.nameLayout);
        nameErrorInput = view.findViewById(R.id.nameInputError);
        emailErrorInput = view.findViewById(R.id.emailInputError);
        passwordErrorInput = view.findViewById(R.id.passwordInputError);
        confirmPasswordErrorInput = view.findViewById(R.id.confirmPasswordInputError);
        passwordRepeatInput = view.findViewById(R.id.passwordRegistrationRepatInput);
        passwordRepeatLayout = view.findViewById(R.id.passwordRegistrationRepatLayout);
        progressBar = view.findViewById(R.id.registerProgress);
        googlebtn=view.findViewById(R.id.googleBtnReg);
    }

    @Override
    public void navigateToLogin() {
        NavController controller = Navigation.findNavController(getView());
        controller.navigate(RegisterPageDirections.actionRegisterPageToLoginPage());

    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(requireContext(), HomeActivity.class);
        startActivity(intent);
        requireActivity().finish();


    }


}