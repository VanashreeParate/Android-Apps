package com.example.flipr;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class fblogin extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginManager.getInstance().logInWithReadPermissions(fblogin.this, Arrays.asList("public_profile"));
        callbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken!=null && accessToken.isExpired()==false)
        {
            startActivity(new Intent(fblogin.this, MainActivity3.class));
            finish();
        }

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Intent intent = new Intent(fblogin.this, MainActivity3.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(@NonNull FacebookException exception) {
                        // App code
                    }
                });
    }

}

// keytool -exportcert -alias androiddebugkey -keystore "C:\Users\vanashree parate\.android\debug.keystore" | "C:\Users\vanashree parate\Downloads\openssl-0.9.8e_X64\bin\openssl" sha1 -binary | "C:\Users\vanashree parate\Downloads\openssl-0.9.8e_X64\bin\openssl" base64
