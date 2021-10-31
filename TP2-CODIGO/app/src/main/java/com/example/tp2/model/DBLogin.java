package com.example.tp2.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp2.data.User;
import com.example.tp2.presenter.LoginPresenter;
import com.example.tp2.view.LoginActivity;

import java.util.Date;

public class DBLogin {

    public LoginActivity activity;
    public User user;

    public DBLogin(LoginActivity activity, User user) {
        this.activity = activity;
        this.user = user;
    }

    public void insertInDB(){

        MyOpenHelper dbHelper = new MyOpenHelper(activity.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            Date date1 = java.util.Calendar.getInstance().getTime();

            ContentValues cv = new ContentValues();
            cv.put("username", user.getEmail());
            cv.put("date", String.valueOf(date1));
            db.insert("user_login", null, cv);
        }

        db.close();

    }

}
