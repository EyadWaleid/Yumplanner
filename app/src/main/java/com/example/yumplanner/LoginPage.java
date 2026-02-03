package com.example.yumplanner;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends Fragment {
    TextInputLayout emailLayout;
    TextInputLayout passwordLayout;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;
    Button forgetPasswordBtn;
    Button signUpBtn;
    MaterialButton loginBtn;
    MaterialButton googlebtn;




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



         return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginBtn.setOnClickListener(v ->{
            if(emailInput.getText().toString().isEmpty()){
                emailLayout.setError("Enter your email");
                return;
            }
            if(passwordInput.getText().toString().isEmpty()){
                passwordLayout.setError("Enter your password");
                return;
            }
            Intent intent = new Intent(requireContext(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();

        });
        forgetPasswordBtn.setOnClickListener(v -> {
            NavController controller= Navigation.findNavController(getView());
            controller.navigate(com.example.yumplanner.LoginPageDirections.actionLoginPageToForgetPasswordPage());
        });
        signUpBtn.setOnClickListener(v -> {
            NavController controller= Navigation.findNavController(getView());
            controller.navigate(com.example.yumplanner.LoginPageDirections.actionLoginPageToRegisterPage());

        });

    }
}