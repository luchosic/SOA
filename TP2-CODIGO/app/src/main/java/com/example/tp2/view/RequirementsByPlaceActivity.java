package com.example.tp2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.presenter.RequirementsByPlacePresenter;
import com.example.tp2.R;

public class RequirementsByPlaceActivity extends AppCompatActivity{

    private RequirementsByPlacePresenter presenter;

    TextView requirementsTeatro;
    TextView requirementsCancha;
    TextView requirementsVuelosNacionales;
    TextView requirementsVuelosInternacionales;
    TextView textSelectedPlace;
    public Button volverBoton;
    public Button continueButton;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements_by_place);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedPlace = extras.getString("selectedPlace");
        userEmail = extras.getString("useremail");

        textSelectedPlace = findViewById(R.id.selectedPlace);
        requirementsTeatro = findViewById(R.id.requisitosTeatro);
        requirementsCancha = findViewById(R.id.requisitosCancha);
        requirementsVuelosNacionales = findViewById(R.id.requisitosVuelosNacionales);
        requirementsVuelosInternacionales = findViewById(R.id.requisitosVuelosInternacionales);
        continueButton = findViewById(R.id.continuarButton);
        volverBoton = findViewById(R.id.volverButton);

        textSelectedPlace.setText(selectedPlace);
        requirementsTeatro.setVisibility(View.INVISIBLE);
        requirementsCancha.setVisibility(View.INVISIBLE);
        requirementsVuelosNacionales.setVisibility(View.INVISIBLE);
        requirementsVuelosInternacionales.setVisibility(View.INVISIBLE);

        switch (selectedPlace){
            case "Cine/Teatro":
                requirementsTeatro.setVisibility(View.VISIBLE);
            break;

            case "Cancha":
                requirementsCancha.setVisibility(View.VISIBLE);
            break;

            case "Vuelos nacionales":
                requirementsVuelosNacionales.setVisibility(View.VISIBLE);
            break;

            case "Vuelos internacionales":
                requirementsVuelosInternacionales.setVisibility(View.VISIBLE);
            break;
        }

        presenter = new RequirementsByPlacePresenter(this);
        presenter.setupSensorManager();
        presenter.startSensoring();

        continueButton.setEnabled(false); //Se habilita con el shake

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContinuar = new Intent(getApplicationContext(), TemperatureActivity.class);
                intentContinuar.putExtra("selectedPlace", selectedPlace);
                intentContinuar.putExtra("useremail", userEmail);
                startActivity(intentContinuar);
            }
        });

        volverBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), MainActivity.class);
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
