package com.example.tp2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.data.PlaceMostVisitedMetric;
import com.example.tp2.data.UserConexionMetric;
import com.example.tp2.presenter.MainPresenter;
import com.example.tp2.presenter.MetricsPresenter;

import java.util.ArrayList;

public class MetricsActivity extends AppCompatActivity {

    public MetricsPresenter presenter;
    public ArrayList<UserConexionMetric> userConexionMetric;
    public Button volverBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);

        volverBoton = findViewById(R.id.volverButton);
        TextView loguinByHour = findViewById(R.id.metricaLogueos);
        TextView placesMostVisited = findViewById(R.id.metricaLugares);;
        presenter = new MetricsPresenter(this);

        loguinByHour.setText("METRICAS");
        placesMostVisited.setText("aca van las metricas por lugar");

        userConexionMetric = presenter.getUserConexionMetric();

        for(UserConexionMetric valores : userConexionMetric){
            System.out.println("Franja: " + valores.getFranja() + " Cantidad de logueos: " + valores.getCantidadLogueos());
        }

        volverBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentVolver);
            }
        });

    }
}
