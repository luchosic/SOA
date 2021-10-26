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

public class TemperatureActivity extends AppCompatActivity implements SensorEventListener {

    Button volverButton;
    Button irButton;
    TextView textoTemperatura;

    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        textoTemperatura = findViewById(R.id.textoTemperatura);

        irButton = findViewById(R.id.irButton);

        irButton.setEnabled(false);

        irButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContinuar = new Intent(getApplicationContext(), FinalActivity.class);
                startActivity(intentContinuar);
            }
        });

        volverButton = (Button)findViewById(R.id.volverButton);

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), RequirementsByPlaceActivity.class);
                startActivity(intentVolver);
            }
        });

    }



    // Metodo para iniciar el acceso a los sensores
    protected void startSensoring() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Metodo para parar la escucha de los sensores
    private void stopSensoring() {
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE));
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
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    textoTemperatura.setText(event.values[0] + " °C");

                    //si la temperatura es mayor a 20 C, habilito el boton para avanzar
                    if(event.values[0] >= 20){
                        irButton.setEnabled(true);
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
