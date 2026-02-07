package com.example.yumplanner.data.dataSource.auth.remote;

import android.content.Context;
import android.os.CancellationSignal;
import android.util.Log;

import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.CustomCredential;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.NoCredentialException;

import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Auth.presenter.forgetpassword.ForgetPasswordPresenter;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AuthenticatDataSource {
    private FirebaseAuth firebaseAuth;
    private Executor executor;

    public AuthenticatDataSource() {
        firebaseAuth = FirebaseAuth.getInstance();
        executor = Executors.newSingleThreadExecutor();
    }

    // Login with email and password
    public void login(String email, String password, AuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user= firebaseAuth.getCurrentUser();;
                        callback.onSuccess(user);
                    } else {
                        Exception e = task.getException();
                        if (e != null) {
                            Log.e("login", "Firebase login error", e);
                            if (e instanceof FirebaseAuthInvalidUserException || e instanceof FirebaseAuthInvalidCredentialsException) {
                                callback.onError("your email or password are wrong");
                            } else if (e instanceof java.net.ConnectException) {
                                callback.onError("Check your connectivty");

                            }
                        }
                    }
                });
    }

    // Sign up with email and password
    public void signUp(String email, String password, AuthCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        callback.onSuccess(user);
                    } else {
                        Exception e =task.getException();
                      if (e instanceof FirebaseAuthUserCollisionException) {
                           callback.onError("Email already registered");
                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            callback.onError("Invalid email address");
                        } else if (e instanceof FirebaseNetworkException) {
                            callback.onError("Check your internet connection");
                        } else {
                            callback.onError("Please try again later.");
                        }
                      Log.d("signUp",e.getClass().toString());

                        callback.onError(task.getException().getMessage());
                    }
                });
    }

    // Login with Google using Credential Manager
    public void loginWithGoogle(Context context, AuthCallback callback) {
        //request to get the token to provide  to firebase
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build();
        //showing the google accounts only

        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();

        CredentialManager credentialManager = CredentialManager.create(context);
        // put all the credentials needed to select google account
        credentialManager.getCredentialAsync(
                context,
                request,
                new CancellationSignal(), // used to cancel the choosing operation for an account as he could press button exit or touch the background so the dialog vanish
                executor,
                new androidx.credentials.CredentialManagerCallback<androidx.credentials.GetCredentialResponse, GetCredentialException>() {
                    @Override
                    public void onResult(androidx.credentials.GetCredentialResponse result) {
                        handleSignIn(result.getCredential(), callback);
                    }
                    @Override
                    public void onError(GetCredentialException e) {
                        if (e instanceof GetCredentialCancellationException) {
                            //no selection exception
                            runOnMainThread(() -> callback.onError("You did not choose any account"));}
                          else if (e instanceof NoCredentialException) {
                            // there is no google accounts
                            runOnMainThread(() -> callback.onError("You have to register first"));
                        } else {
                            runOnMainThread(() -> callback.onError("failed sign in try again"));

                        }

                    }
                }
        );
    }
    public void RegisterWithGoogle(Context context, AuthCallback callback) {
        //request to get the token to provide  to firebase
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build();
        //showing the google accounts only

        GetCredentialRequest request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build();

        CredentialManager credentialManager = CredentialManager.create(context);
        // put all the credentials needed to select google account
        credentialManager.getCredentialAsync(
                context,
                request,
                new CancellationSignal(), // used to cancel the choosing operation for an account as he could press button exit or touch the background so the dialog vanish
                executor,
                new androidx.credentials.CredentialManagerCallback<androidx.credentials.GetCredentialResponse, GetCredentialException>() {
                    @Override
                    public void onResult(androidx.credentials.GetCredentialResponse result) {
                        handleSignIn(result.getCredential(), callback);
                    }

                    @Override
                    public void onError(GetCredentialException e) {
                        if (e instanceof GetCredentialCancellationException) {
                            //no selection exception
                            runOnMainThread(() -> callback.onError("You did not choose any account"));}
                     else {
                            runOnMainThread(() -> callback.onError("failed sign in try again"));

                        }

                    }
                }
        );
    }

    private void handleSignIn(Credential credential, AuthCallback callback) {
        if (credential instanceof CustomCredential) {
            CustomCredential customCredential = (CustomCredential) credential;

            if (customCredential.getType().equals(GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL)) {
                try {
                    GoogleIdTokenCredential googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(customCredential.getData());
                    firebaseAuthWithGoogle(googleIdTokenCredential.getIdToken(), callback);
                } catch (Exception e) {
                    runOnMainThread(() ->{
                         callback.onError("Try again later");
                        Log.d("tokenError",e.getMessage());
                    });
                }
                return;
            }
        }
        runOnMainThread(() -> callback.onError("your google account not validate"));
    }

    private void firebaseAuthWithGoogle(String idToken, AuthCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user= firebaseAuth.getCurrentUser();;

                        runOnMainThread(() -> callback.onSuccess(user));
                    } else {
                        Exception e=  task.getException();
                        if (e instanceof FirebaseNetworkException) {
                            callback.onError("Check your internet connection");
                        } else {
                            runOnMainThread(() -> callback.onError("Try again later"));
                        }

                    }
                });
    }
    public  void resetPassword(String email, ForgetPaswordCallback callback){
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("ResetPassword", "Email sent.");
                        callback.OnSuccessForgetPassword();

                    } else {
                        Exception e= task.getException();

                        if (e instanceof FirebaseAuthInvalidUserException) {
                            callback.onError( "No account found with this email");

                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            callback.onError("Enter a valid email address");


                        } else if (e instanceof FirebaseNetworkException) {
                            callback.onError("Check your internet connection");
                        } else {
                            callback.onError("Failed to send reset email. Try again later");

                        }

                        Log.e("ResetPassword", "Error", e);

                    }
                });
    }

    private void runOnMainThread(Runnable runnable) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(runnable);
    }
    // Sign out
    public void signOut() {
        firebaseAuth.signOut();
    }
}