package com.example.articles.controller.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.articles.R;
import com.example.articles.controller.ActionsManager;
import com.example.articles.controller.fragments.LoginFragment;
import com.example.articles.model.Constants;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionsManager.attachFragment(this, R.id.login_container, new LoginFragment(), Constants.LOGIN_FRAGMENT_TAG);
    }
}
