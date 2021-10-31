package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AutenticationCodeActivity extends AppCompatActivity {

    Button coninueButton;
    Button volverButton;

    public EditText codigoIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentication_code);

        codigoIngresado = (EditText) findViewById(R.id.codigo);
        volverButton = (Button)findViewById(R.id.volverButton);
        coninueButton = (Button) findViewById(R.id.ingresarButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String randomCode = extras.getString("randomCode");
        String userEmail = extras.getString("useremail");

        coninueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((codigoIngresado.getText().toString()).equals(randomCode)){
                    Intent intentContinuar = new Intent(getApplicationContext(), LoginActivity.class);
                    intentContinuar.putExtra("useremail", userEmail);
                    startActivity(intentContinuar);
                }
                else{
                    codigoIngresado.setError("El código ingresado es incorrecto");
                }
            }
        });

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentVolver);
            }
        });

    }
}
