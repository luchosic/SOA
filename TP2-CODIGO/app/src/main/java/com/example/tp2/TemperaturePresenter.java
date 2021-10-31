package com.example.tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class TemperaturePresenter implements SensorEventListener{
    private SensorManager sensorManager;
    private TemperatureActivity activity;

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
        activity.textoTemperatura.setText(event.values[0] + " °C");

        //si la temperatura es mayor a 20 C, habilito el boton para avanzar
        if (event.values[0] >= 20) {
            activity.irButton.setEnabled(true);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
