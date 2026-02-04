package com.example.yumplanner.presentation.Auth.view;

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

import com.example.yumplanner.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterPage extends Fragment {
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
     return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpBtn.setOnClickListener(v -> {
            if(emailInput.getText().toString().isEmpty() || nameInput.getText().toString().isEmpty()||passwordInput.getText().toString().isEmpty()||passwordRepeatInput.getText().toString().isEmpty()){
                if(emailInput.getText().toString().isEmpty()){
                    emailLayout.setError("Enter your email");
                }
                if(nameInput.getText().toString().isEmpty()){
                    nameLayout.setError("Enter your name");

                }
                if (passwordInput.getText().toString().isEmpty()){
                    passwordLayout.setError("Enter your password");
                }
                if(passwordRepeatInput.getText().toString().isEmpty()){
                    passwordRepeatLayout.setError("Repeat your password");
                }
                   return;
            }
            if(!passwordRepeatInput.getText().toString().equals(passwordInput.getText().toString())){
                passwordRepeatLayout.setError("enter the same password");
            }
        });
        loginBtn.setOnClickListener(v->{
            NavController controller= Navigation.findNavController(getView());
            controller.navigate(RegisterPageDirections.actionRegisterPageToLoginPage());

        });
    }
}