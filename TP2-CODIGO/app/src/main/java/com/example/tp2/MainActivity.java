package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp2.data.JavaMail;

public class MainActivity extends AppCompatActivity {

    EditText mailUser;
    public EditText mEmail;
    public String mSubject;
    public String mMessage;

    Button codigoButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentication);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mEmail = (EditText) findViewById(R.id.mailCodigo);
        mMessage = "Codigo para ingresar";
        mSubject = "1234";

        codigoButton = (Button) findViewById(R.id.codigoButton);

        codigoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();

            }
        });

    }


    private void sendMail() {

        String mail = mEmail.getText().toString().trim();
        String subject = "Codigo para ingresar";
        String message = "1234";

        //Send Mail
        JavaMail javaMail = new JavaMail(this,mail,subject,message);

        javaMail.execute();

    }
}