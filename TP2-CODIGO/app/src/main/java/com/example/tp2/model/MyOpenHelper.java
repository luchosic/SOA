package com.example.tp2.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "covidlessappTEST";
    private static final int DB_VERSION = 2;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user_login(_id INTEGER PRIMARY KEY AUTOINCREMENT, username STRING, hora INTEGER)");
        db.execSQL("CREATE TABLE places_visited(_id INTEGER PRIMARY KEY AUTOINCREMENT, place STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}