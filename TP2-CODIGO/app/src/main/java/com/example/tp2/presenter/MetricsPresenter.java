package com.example.tp2.presenter;

import com.example.tp2.data.PlaceMostVisitedMetric;
import com.example.tp2.data.SessionManager;
import com.example.tp2.data.TokenRefresher;
import com.example.tp2.data.UserConexionMetric;
import com.example.tp2.model.DBInsertPlaces;
import com.example.tp2.model.DBMetrics;
import com.example.tp2.view.MainActivity;
import com.example.tp2.view.MetricsActivity;

import java.util.ArrayList;
import java.util.List;

public class MetricsPresenter {

    public MetricsActivity activity;

    public MetricsPresenter(MetricsActivity activity) {
        this.activity = activity;
    }

    public ArrayList<UserConexionMetric> getUserConexionMetric(){
        DBMetrics model = new DBMetrics(activity);

        return model.calculateUserConexion();
    }

    public ArrayList<PlaceMostVisitedMetric> getPlaceMostVisitedMetric(){
        DBMetrics model = new DBMetrics(activity);

        return model.calculatePlaceMostVisited();
    }

}
