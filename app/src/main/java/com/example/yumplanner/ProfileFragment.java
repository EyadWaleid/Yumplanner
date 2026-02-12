package com.example.yumplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.credentials.ClearCredentialStateRequest;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.fragment.app.Fragment;

import android.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yumplanner.presentation.Auth.view.AuthenticationActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executors;

public class ProfileFragment extends Fragment {

    MaterialButton logoutBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        logoutBtn = view.findViewById(R.id.logouBtn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutBtn.setOnClickListener(v -> signOut());
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();

        androidx.credentials.CredentialManager credentialManager =
                androidx.credentials.CredentialManager.create(requireContext());

        ClearCredentialStateRequest clearRequest =
                new ClearCredentialStateRequest();

        credentialManager.clearCredentialStateAsync(
                clearRequest,
                new CancellationSignal(),
                Executors.newSingleThreadExecutor(),
                new CredentialManagerCallback<Void, ClearCredentialException>() {

                    @Override
                    public void onResult(Void result) {
                        Intent intent = new Intent(requireContext(), AuthenticationActivity.class);
                        startActivity(intent);
                        requireActivity().finish();                    }

                    @Override
                    public void onError(@NonNull ClearCredentialException e) {
                    }
                }
        );


    }
}
