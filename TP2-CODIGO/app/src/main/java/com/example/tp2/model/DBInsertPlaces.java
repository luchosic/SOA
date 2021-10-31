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
            System.out.println("lugar en el insert: " + place);

            ContentValues cv = new ContentValues();
            cv.put("place", place);
            db.insert("places_visited", null, cv);

            //El siguiente codigo es para visualizar lo que se esta guardando en la base de datos.
            //Eliminarlo para entegar
            Cursor c = db.rawQuery("SELECT _id, place FROM places_visited", null);

            if (c != null) {
                c.moveToFirst();
                do {
                    //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    @SuppressLint("Range") String place = c.getString(c.getColumnIndex("place"));
                    System.out.println("Place: " + place);
                } while (c.moveToNext());
            }

            //Cerramos el cursor y la conexion con la base de datos
            c.close();
        }

        db.close();

    }

}