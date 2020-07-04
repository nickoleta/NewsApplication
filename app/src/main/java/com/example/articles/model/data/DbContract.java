package com.example.articles.model.data;

import android.provider.BaseColumns;

final class DbContract {

    static final class UsersEntry implements BaseColumns {
        static final String TABLE_NAME = "users";
        static final String USERNAME = "username";
        static final String PASSWORD = "password";
    }

}
