package com.example.tp2.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp2.data.User;
import com.example.tp2.view.LoginActivity;
import com.example.tp2.view.MainActivity;

import java.util.Date;

public class DBInsertPlaces {

    public MainActivity activity;
    public String place;

    public DBInsertPlaces(MainActivity activity, String place) {
        this.activity = activity;
        this.place = place;
    }

    public void insertInDB(){

        MyOpenHelper dbHelper = new MyOpenHelper(activity.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("place", place);
            db.insert("places_visited", null, cv);
        }

        db.close();

    }

}
