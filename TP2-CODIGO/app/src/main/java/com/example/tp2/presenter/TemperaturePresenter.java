package com.example.tp2.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.tp2.R;
import com.example.tp2.data.Event;
import com.example.tp2.data.TrustRequest;
import com.example.tp2.view.TemperatureActivity;

public class TemperaturePresenter implements SensorEventListener{
    private SensorManager sensorManager;
    private TemperatureActivity activity;
    private TrustRequest trustRequest;
    Event eventoALoguear = new Event();
    float currentValue = 0;

    public TemperaturePresenter(TemperatureActivity activity) {
        this.activity = activity;
    }

    public void setupSensorManager() {
        sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
    }

    // Metodo para iniciar el acceso a los sensores
    public void startSensoring() {
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Metodo para parar la escucha de los sensores
    public void stopSensoring() {
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorManager.unregisterListener(this, accelerometerSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(currentValue != event.values[0]){
            //Logueo evento
            trustRequest = new TrustRequest(activity.getApplicationContext());
            eventoALoguear.setEnv(activity.getResources().getString(R.string.APIEnvoriment));
            eventoALoguear.setType_events("Detección cambio de Temperatura");
            eventoALoguear.setDescription("La temperatura medida es: " + event.values[0] + " °C");
            trustRequest.registerEvent(eventoALoguear);

            currentValue = event.values[0];

            activity.textoTemperatura.setText(event.values[0] + " °C");
        }

        //si la temperatura es mayor a 20 C, habilito el boton para avanzar
        if (event.values[0] >= 20) {
            activity.irButton.setEnabled(true);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
