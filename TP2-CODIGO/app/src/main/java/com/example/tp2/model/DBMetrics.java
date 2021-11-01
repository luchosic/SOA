package com.example.tp2.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp2.data.PlaceMostVisitedMetric;
import com.example.tp2.data.UserConexionMetric;
import com.example.tp2.presenter.MetricsPresenter;
import com.example.tp2.view.MetricsActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBMetrics {

    public MetricsActivity activity;

    public DBMetrics(MetricsActivity activity) {
        this.activity = activity;
    }

    public ArrayList<UserConexionMetric> calculateUserConexion(){

        ArrayList<UserConexionMetric> userConexionMetric = new ArrayList<UserConexionMetric>();

        MyOpenHelper dbHelper = new MyOpenHelper(activity.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            Cursor cursorUserConexion = db.rawQuery("SELECT 'Franja 0 a 6' AS franja, COUNT(*) AS cantidad FROM user_login WHERE hora BETWEEN 0 and 6 UNION SELECT 'Franja 7 a 12' AS franja, COUNT(*) AS cantidadLogueos FROM user_login WHERE hora BETWEEN 7 and 12 UNION SELECT 'Franja 13 a 19' AS franja, COUNT(*) AS cantidad FROM user_login WHERE hora BETWEEN 13 and 19 UNION SELECT 'Franja 20 a 23' AS logueo, COUNT(*) AS cantidad FROM user_login WHERE hora BETWEEN 20 and 23;", null);

            if (cursorUserConexion.moveToFirst()) {
                do {
                    userConexionMetric.add(new UserConexionMetric(
                            cursorUserConexion.getString(0),
                            cursorUserConexion.getString(1)));
                } while (cursorUserConexion.moveToNext());
            }

            cursorUserConexion.close();
        }

        db.close();

        return userConexionMetric;

    }

    public ArrayList<PlaceMostVisitedMetric> calculatePlaceMostVisited(){

        ArrayList<PlaceMostVisitedMetric> placeMostVisitedMetric = new ArrayList<PlaceMostVisitedMetric>();

        MyOpenHelper dbHelper = new MyOpenHelper(activity.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {

        }

        db.close();

        return placeMostVisitedMetric;

    }

}
