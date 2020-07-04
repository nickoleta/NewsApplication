package com.example.articles.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.articles.R;
import com.example.articles.controller.ActionsManager;
import com.example.articles.model.data.DbManager;
import com.example.articles.model.data.InputValidator;
import com.example.articles.model.dto.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.articles.controller.ActionsManager.createOnFocusChangeListeners;
import static com.example.articles.controller.ActionsManager.replaceFragment;
import static com.example.articles.model.Constants.LOGIN_FRAGMENT_TAG;

public class RegistrationFragment extends Fragment {

    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;
    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputEditText confirmPassword;
    private Button registerButton;
    private Button cancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        initViews(view);
        initActions();

        createOnFocusChangeListeners(username, usernameLayout);
        createOnFocusChangeListeners(password, passwordLayout);
        createOnFocusChangeListeners(confirmPassword, confirmPasswordLayout);

        return view;
    }

    private void initViews(View view) {
        this.usernameLayout = view.findViewById(R.id.username_layout);
        this.passwordLayout = view.findViewById(R.id.password_layout);
        this.confirmPasswordLayout = view.findViewById(R.id.confirm_password_layout);
        this.username = view.findViewById(R.id.username_registration);
        this.password = view.findViewById(R.id.password_registration);
        this.confirmPassword = view.findViewById(R.id.confirm_password_registration);
        this.registerButton = view.findViewById(R.id.register_button);
        this.cancelButton = view.findViewById(R.id.cancel_registration_button);
    }

    private void initActions() {
        registerButton.setOnClickListener((View v) -> {
            if (isValidRegistration()) {
                replaceFragment(this, R.id.login_container, new LoginFragment(), LOGIN_FRAGMENT_TAG);
            }
        });

        cancelButton.setOnClickListener((View v) -> replaceFragment(this, R.id.login_container, new LoginFragment(), LOGIN_FRAGMENT_TAG));
    }

    private boolean isValidRegistration() {
        final String usernameStr = ActionsManager.getString(username);
        final String passwordStr = ActionsManager.getString(password);
        final String confirmPassStr = ActionsManager.getString(confirmPassword);
        if (!InputValidator.isValidUsername(usernameStr)) {
            usernameLayout.setError("Please enter a valid username");
            return false;
        }
        if (!InputValidator.isValidPassword(passwordStr)) {
            passwordLayout.setError("A strong password should be at least 8 symbols long and should contain at least one capital letter and a special symbol");
            return false;
        }
        if (!InputValidator.isValidConfirmedPassword(passwordStr, confirmPassStr)) {
            confirmPasswordLayout.setError("Passwords do not match");
            return false;
        }
        User newUser = new User(usernameStr, passwordStr);
        DbManager.getInstance(getContext()).saveUser(newUser);
        return true;
    }
}
