package com.example.tp2.model;

import android.content.ContentValues;
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
