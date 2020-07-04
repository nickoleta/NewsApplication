package com.example.articles.controller;

import android.content.Context;
import android.text.Editable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.articles.model.data.InputValidator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ActionsManager {

    public static void createOnFocusChangeListeners(TextInputEditText object, TextInputLayout layout) {
        object.setOnFocusChangeListener((view1, hasFocus) -> {
            if (!hasFocus) {
                if (InputValidator.isValidString(getString(object))) {
                    layout.setError(null);
                }
            }
        });
    }

    public static void replaceFragment(Fragment fragment, int containerViewId, Fragment newFragment, String tag) {
        Objects.requireNonNull(fragment.getActivity())
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, newFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public static void replaceFragment(Context context, int containerViewId, Fragment newFragment, String tag) {
        ((AppCompatActivity) context)
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, newFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public static void attachFragment(AppCompatActivity activity, int containerViewId, Fragment newFragment, String tag) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, newFragment, tag)
                .commit();
    }

    public static String getString(TextInputEditText textInputEditText) {
        Editable text = textInputEditText.getText();
        return text == null ? null : text.toString();
    }
}
