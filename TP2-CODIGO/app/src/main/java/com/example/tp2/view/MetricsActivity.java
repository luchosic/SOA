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
    public Button volverBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);

        volverBoton = findViewById(R.id.volverButton);
        TextView loguinByHour = findViewById(R.id.metricaLogueos);
        TextView placesMostVisited = findViewById(R.id.metricaLugares);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String userEmail = extras.getString("useremail");

        presenter = new MetricsPresenter(this);

        loguinByHour.setText(presenter.getUserConexionMetric().toString());
        placesMostVisited.setText(presenter.getPlaceMostVisitedMetric().toString());

        volverBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), MainActivity.class);
                intentVolver.putExtra("useremail", userEmail);
                startActivity(intentVolver);
            }
        });

    }
}
