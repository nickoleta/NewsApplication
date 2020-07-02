package com.example.news.controller.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.news.ActionsManager;
import com.example.news.Constants;
import com.example.news.R;
import com.example.news.controller.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionsManager.attachFragment(this, R.id.login_container, new LoginFragment(), Constants.LOGIN_FRAGMENT_TAG);
    }
}
