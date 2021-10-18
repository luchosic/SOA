package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //EditText mailUser = (EditText) findViewById(R.id.emailCode);
    Button codigoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentication);

        codigoButton = (Button) findViewById(R.id.codigoButton);

        codigoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"zevemmopabo-8478@yopmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "CovidLess - Codigo de ingreso");
                i.putExtra(Intent.EXTRA_TEXT   , "¡Hola! Su código de ingreso es: 1234");
                try {
                    startActivity(Intent.createChooser(i, "Enviando correo..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "No hay clientes de correo electrónico instalados.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}