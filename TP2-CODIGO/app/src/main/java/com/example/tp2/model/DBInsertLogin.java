package com.example.tp2.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp2.data.User;
import com.example.tp2.view.LoginActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DBInsertLogin {

    public Context context;
    public User user;

    public DBInsertLogin(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public void insertInDB(){

        MyOpenHelper dbHelper = new MyOpenHelper(context.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT-3"));

            ContentValues cv = new ContentValues();
            cv.put("username", user.getEmail());
            cv.put("hora", calendar.get(Calendar.HOUR_OF_DAY));
            db.insert("user_login", null, cv);
        }

        db.close();

    }

}
