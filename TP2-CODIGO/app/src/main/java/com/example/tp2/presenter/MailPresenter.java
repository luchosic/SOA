package com.example.tp2.presenter;

import android.content.Intent;
import android.util.Patterns;

import com.example.tp2.data.InternetConnection;
import com.example.tp2.data.JavaMail;
import com.example.tp2.view.MailActivity;

public class MailPresenter {

    public MailActivity activity;

    public MailPresenter(MailActivity activity) {
        this.activity = activity;
    }

    public void sendMail(){

        if(!Patterns.EMAIL_ADDRESS.matcher(activity.mEmail.getText().toString()).matches()){
            activity.mailFailure("Debe ingresar un mail válido");
        }
        else{
            if(InternetConnection.isOnline(activity)) {
                String randomCode;
                randomCode = String.valueOf((int)(Math.random() * 9000) + 1000);

                sendMail(randomCode);

                activity.mailSuccessful(randomCode);
            }else{
                activity.mailFailure("No hay conexión a internet");
            }
        }
    }


    private void sendMail(String randomCode) {

        String mail = activity.mEmail.getText().toString().trim();
        String subject = "Bienvenido a CovidLess!";
        String body = "Su código de verificación es: " + randomCode;

        //Armo y envio mail
        JavaMail javaMail = new JavaMail(activity,mail,subject,body);
        javaMail.execute();

    }
}
