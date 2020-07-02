package com.example.news.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.news.ActionsManager;
import com.example.news.R;
import com.example.news.controller.activities.MainActivity;
import com.example.news.model.data.DbManager;
import com.example.news.model.data.InputValidator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.news.ActionsManager.createOnFocusChangeListeners;
import static com.example.news.ActionsManager.replaceFragment;
import static com.example.news.Constants.REGISTRATION_FRAGMENT_TAG;

public class LoginFragment extends Fragment {

    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private TextInputEditText username;
    private TextInputEditText password;
    private Button loginButton;
    private TextView registrationButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        initViews(view);
        initActions();

        createOnFocusChangeListeners(username, usernameLayout);
        createOnFocusChangeListeners(password, passwordLayout);

        usernameLayout.requestFocus();

        return view;
    }

    private void initViews(View view) {
        this.usernameLayout = view.findViewById(R.id.username_layout);
        this.passwordLayout = view.findViewById(R.id.password_layout);
        this.username = view.findViewById(R.id.username_login);
        this.password = view.findViewById(R.id.password_login);
        this.loginButton = view.findViewById(R.id.login_button);
        this.registrationButton = view.findViewById(R.id.registration_button);
    }

    private void initActions() {
        loginButton.setOnClickListener((View v) -> {
            final String usernameStr = ActionsManager.getString(username);
            final String passwordStr = ActionsManager.getString(password);
            if (isValidLogin(usernameStr, passwordStr)) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        registrationButton.setOnClickListener((View v) -> replaceFragment(this, R.id.login_container, new RegistrationFragment(), REGISTRATION_FRAGMENT_TAG));
    }

    private boolean isValidLogin(String usernameStr, String passStr) {
        if (!InputValidator.isValidString(usernameStr)) {
            usernameLayout.setError("Please enter your username");
            return false;
        }
        if (!InputValidator.isValidString(passStr)) {
            passwordLayout.setError("Please enter your password");
            return false;
        }
        if (!DbManager.getInstance(getActivity()).existsUser(usernameStr, passStr)) {
            usernameLayout.setError("Invalid username or password");
            return false;
        }
        return true;
    }

}
