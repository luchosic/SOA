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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentication_code);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String randomCode = extras.getString("randomCode");

        coninueButton = (Button) findViewById(R.id.ingresarButton);

        coninueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}
