package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp2.data.JavaMail;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mailUser;
    public EditText mEmail;
    public String randomCode;

    Button codigoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentication);

        mEmail = (EditText) findViewById(R.id.mailCodigo);

        codigoButton = (Button) findViewById(R.id.codigoButton);

        codigoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomCode = String.valueOf((int)(Math.random() * 9000) + 1000);

                sendMail(randomCode);

                Intent intent = new Intent(MainActivity.this, AutenticationCodeActivity.class);
                intent.putExtra("randomCode", randomCode);
                startActivity(intent);
            }
        });

    }


    private void sendMail(String randomCode) {

        String mail = mEmail.getText().toString().trim();
        String subject = "Codigo para ingresar";

        //Send Mail
        JavaMail javaMail = new JavaMail(this,mail,subject,randomCode);

        javaMail.execute();

    }
}