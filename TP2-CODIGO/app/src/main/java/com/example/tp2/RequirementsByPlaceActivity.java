package com.example.tp2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RequirementsByPlaceActivity extends AppCompatActivity implements SensorEventListener {

    TextView requirementsTeatro;
    TextView requirementsCancha;
    TextView requirementsVuelosNacionales;
    TextView requirementsVuelosInternacionales;
    TextView textSelectedPlace;
    Button volverBoton;
    Button continueButton;

    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements_by_place);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedPlace = extras.getString("selectedPlace");

        textSelectedPlace = findViewById(R.id.selectedPlace);
        requirementsTeatro = findViewById(R.id.requisitosTeatro);
        requirementsCancha = findViewById(R.id.requisitosCancha);
        requirementsVuelosNacionales = findViewById(R.id.requisitosVuelosNacionales);
        requirementsVuelosInternacionales = findViewById(R.id.requisitosVuelosInternacionales);

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


        continueButton = findViewById(R.id.continuarButton);

        //continueButton.setEnabled(false);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContinuar = new Intent(getApplicationContext(), TemperatureActivity.class);
                startActivity(intentContinuar);
            }
        });

        volverBoton = findViewById(R.id.volverButton);

        volverBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), PlacesToGoActivity.class);
                startActivity(intentVolver);
            }
        });

    }

    // Metodo para iniciar el acceso a los sensores
    protected void startSensoring() {
       mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
 }

    // Metodo para parar la escucha de los sensores
    private void stopSensoring() {
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String txt = "";
        float eventoAnt = 0;

        // Cada sensor puede lanzar un thread que pase por aqui
        // Para asegurarnos ante los accesos simult�neos sincronizamos esto

        synchronized (this)
        {
            //Log.d("sensor", event.sensor.getName());

            switch(event.sensor.getType())
            {
                case Sensor.TYPE_ACCELEROMETER :
                    if ((event.values[0] > 25) || (event.values[1] > 25) || (event.values[2] > 25)) {
                        System.out.println("VIBRACION DETECTADA");
                        continueButton.setEnabled(true);
                    }
                break;

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onStop() {
        stopSensoring();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopSensoring();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        stopSensoring();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        startSensoring();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startSensoring();
    }

}
