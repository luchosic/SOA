package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RequirementsByPlaceActivity extends AppCompatActivity {

    TextView requirementsTeatro;
    TextView requirementsCancha;
    TextView requirementsVuelosNacionales;
    TextView requirementsVuelosInternacionales;
    TextView textSelectedPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements_by_place);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedPlace = extras.getString("selectedPlace");

        textSelectedPlace = (TextView) findViewById(R.id.selectedPlace);
        requirementsTeatro = (TextView) findViewById(R.id.requisitosTeatro);
        requirementsCancha = (TextView) findViewById(R.id.requisitosCancha);
        requirementsVuelosNacionales = (TextView) findViewById(R.id.requisitosVuelosNacionales);
        requirementsVuelosInternacionales = (TextView) findViewById(R.id.requisitosVuelosInternacionales);

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


    }
}
