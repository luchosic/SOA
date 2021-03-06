package com.example.tp2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.presenter.TemperaturePresenter;
import com.example.tp2.R;

public class TemperatureActivity extends AppCompatActivity{

    private TemperaturePresenter presenter;

    public Button volverButton;
    public Button irButton;
    public TextView textoTemperatura;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        textoTemperatura = findViewById(R.id.textoTemperatura);
        irButton = findViewById(R.id.irButton);
        volverButton = (Button)findViewById(R.id.volverButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedPlace = extras.getString("selectedPlace");
        userEmail = extras.getString("useremail");

        presenter = new TemperaturePresenter(this);
        presenter.setupSensorManager();
        presenter.startSensoring();

        irButton.setEnabled(false);

        irButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContinuar = new Intent(getApplicationContext(), FinalActivity.class);
                intentContinuar.putExtra("useremail", userEmail);
                startActivity(intentContinuar);
            }
        });

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), RequirementsByPlaceActivity.class);
                intentVolver.putExtra("selectedPlace", selectedPlace);
                intentVolver.putExtra("useremail", userEmail);
                startActivity(intentVolver);
            }
        });

    }

    @Override
    protected void onStop() {
        presenter.stopSensoring();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.stopSensoring();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        presenter.stopSensoring();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        presenter.startSensoring();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.startSensoring();
    }

}
