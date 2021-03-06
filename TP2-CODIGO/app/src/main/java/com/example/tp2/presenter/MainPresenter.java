package com.example.tp2.presenter;

import com.example.tp2.data.SessionManager;
import com.example.tp2.data.TokenRefresher;
import com.example.tp2.model.DBInsertPlaces;
import com.example.tp2.view.LoginActivity;
import com.example.tp2.view.MainActivity;

public class MainPresenter {
    public MainActivity activity;
    public TokenRefresher tokenRefresher;
    public SessionManager sessionManager;

    public MainPresenter(MainActivity activity){
        this.activity = activity;
        this.tokenRefresher = new TokenRefresher(activity.getApplicationContext());
        this.sessionManager = new SessionManager(activity.getApplicationContext());
    }

    public void iniciarHilo(){
         tokenRefresher.ejecutarThread();
    }

    public void detenerHilo(){
        tokenRefresher.detenerThread();
        sessionManager.endSession();
    }

    public void storePlace(String selectedPlace) {
        DBInsertPlaces model = new DBInsertPlaces(activity, selectedPlace);
        model.insertInDB();
    }


}
