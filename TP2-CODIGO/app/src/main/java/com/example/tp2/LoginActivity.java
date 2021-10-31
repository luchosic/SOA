package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private LoginPresenter presenter;

    Button registroBtn;
    Button loginButton;
    EditText editTextEmail;
    EditText editTextPassword;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.user);
        editTextPassword = findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);
        registroBtn = (Button)findViewById(R.id.registroButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userEmail = extras.getString("useremail");

        editTextEmail.setText(userEmail);

        presenter = new LoginPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginEnServer();

                //Deshabilito botones para que no pueda volver a clickear
                loginButton.setEnabled(false);
                registroBtn.setEnabled(false);
            }
        });

        registroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("useremail", userEmail);
                startActivity(intent);
            }
        });
    }


    protected void loginSuccessful() {
        Intent intentContinuar = new Intent(getApplicationContext(), PlacesToGoActivity.class);
        intentContinuar.putExtra("useremail", userEmail);
        startActivity(intentContinuar);
    }

    protected void loginFailure(String error) {
        System.out.println("entro a la funcion de fail");
        Toast.makeText(getApplicationContext(), "Hubo un error: " + error, Toast.LENGTH_LONG).show();

        //Habilito los botones nuevamente para que pueda intentar loguearse
        loginButton.setEnabled(true);
        registroBtn.setEnabled(true);
    }

}
