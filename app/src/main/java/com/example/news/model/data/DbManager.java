package com.example.news.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.news.model.dto.User;

public class DbManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "news";
    private static final int DB_VERSION = 3;

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + DbContract.UsersEntry.TABLE_NAME + "(" +
                    DbContract.UsersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DbContract.UsersEntry.USERNAME + " TEXT, " +
                    DbContract.UsersEntry.PASSWORD + " TEXT )";

    private static final String DROP_TABLE_USERS = "DROP TABLE IF EXISTS users";

    private static final String SELECT_USER = "SELECT * FROM users";

    private static DbManager instance;

    private DbManager(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DbManager getInstance(Context context) {
        if (instance == null) {
            instance = new DbManager(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        db.execSQL(DROP_TABLE_USERS);
        onCreate(db);
    }

    public void saveUser(User user) {
        ContentValues content = new ContentValues();
        content.put("username", user.getUsername());
        content.put("password", user.getPassword());
        instance.getWritableDatabase().insert(DbContract.UsersEntry.TABLE_NAME, null, content);
    }

    public boolean existsUser(String username, String pass) {
        Cursor cursor = instance.getReadableDatabase().rawQuery(SELECT_USER, null);
        while (cursor.moveToNext()) {
            String currentUsername = cursor.getString(cursor.getColumnIndex("username"));
            String currentPassword = cursor.getString(cursor.getColumnIndex("password"));
            if (currentUsername.equals(username) && currentPassword.equals(pass)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

}
