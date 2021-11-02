package com.example.tp2.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.tp2.data.Event;
import com.example.tp2.data.SessionManager;
import com.example.tp2.data.TrustRequest;
import com.example.tp2.view.RequirementsByPlaceActivity;


public class RequirementsByPlacePresenter implements SensorEventListener{
    private SensorManager sensorManager;
    private RequirementsByPlaceActivity activity;
    private TrustRequest trustRequest;
    Event eventoALoguear = new Event();

    public RequirementsByPlacePresenter(RequirementsByPlaceActivity activity) {
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
        trustRequest = new TrustRequest(activity.getApplicationContext());
        eventoALoguear.setEnv("PROD");
        eventoALoguear.setType_events("Detección Shake");
        eventoALoguear.setDescription("Se ha detectado un shake");

        if ((event.values[0] > 25) || (event.values[1] > 25) || (event.values[2] > 25)) {
            activity.continueButton.setEnabled(true);
            //Logueo evento
            trustRequest.registerEvent(eventoALoguear);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
