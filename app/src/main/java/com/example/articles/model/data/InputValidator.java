package com.example.articles.model.data;

import android.util.Log;

public class InputValidator {

    private static final String VALID_USERNAME_REGEX = "[A-Za-z0-9_]+";
    private static final String VALID_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static boolean isValidUsername(String username) {
        return username != null && !username.isEmpty() && username.matches(VALID_USERNAME_REGEX);
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty() || !password.matches(VALID_PASSWORD_REGEX)) {
            Log.i("Invalid registration", "Invalid password");
            return false;
        }
        return true;
    }

    public static boolean isValidConfirmedPassword(String password, String confirmedPassword) {
        return confirmedPassword != null && !confirmedPassword.isEmpty() && password.equals(confirmedPassword);
    }

    public static boolean isValidString(String string) {
        return string != null && !string.isEmpty();
    }
}
