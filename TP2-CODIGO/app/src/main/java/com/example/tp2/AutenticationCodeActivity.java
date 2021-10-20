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

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String randomCode = extras.getString("randomCode");

        coninueButton = (Button) findViewById(R.id.ingresarButton);

        coninueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((codigoIngresado.getText().toString()).equals(randomCode)){
                    Intent intentContinuar = new Intent(AutenticationCodeActivity.this, LoginActivity.class);
                    startActivity(intentContinuar);
                }
                else{
                    codigoIngresado.setError("El código ingresado es incorrecto");
                }
            }
        });

        /*volverButton = (Button)findViewById(R.id.volverButton);

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(AutenticationCodeActivity.this, MainActivity.class);
                startActivity(intentVolver);
            }
        });*/

    }
}
