package com.example.tp2.presenter;

import com.example.tp2.model.DBInsertPlaces;
import com.example.tp2.view.LoginActivity;
import com.example.tp2.view.MainActivity;

public class MainPresenter {
    public MainActivity activity;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    public void storePlace(String selectedPlace) {
        DBInsertPlaces model = new DBInsertPlaces(activity, selectedPlace);
        model.insertInDB();
    }

}
