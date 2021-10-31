package com.example.tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class SensorAcelerometroPresenter implements SensorEventListener{
    private SensorManager sensorManager;
    private RequirementsByPlaceActivity activity;

    public SensorAcelerometroPresenter(RequirementsByPlaceActivity activity) {
        this.activity = activity;
    }

    public void setupSensorManager() {
        sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
    }

    // Metodo para iniciar el acceso a los sensores
    public void startSensoring() {
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Metodo para parar la escucha de los sensores
    public void stopSensoring() {
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.unregisterListener(this, accelerometerSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if ((event.values[0] > 25) || (event.values[1] > 25) || (event.values[2] > 25)) {
            activity.continueButton.setEnabled(true);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
