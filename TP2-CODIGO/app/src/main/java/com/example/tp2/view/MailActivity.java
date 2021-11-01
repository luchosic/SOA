package com.example.tp2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp2.R;
import com.example.tp2.data.Battery;
import com.example.tp2.data.JavaMail;
import com.example.tp2.presenter.LoginPresenter;
import com.example.tp2.presenter.MailPresenter;

public class MailActivity extends AppCompatActivity {

    public EditText mEmail;
    Button codigoButton;
    public MailPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        mEmail = (EditText) findViewById(R.id.mailCodigo);
        codigoButton = (Button) findViewById(R.id.codigoButton);

        Battery batStatus = new Battery(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Su porcentaje de batería es: " + batStatus.getBatteryPercentage() + "%", Toast.LENGTH_LONG).show();

        presenter = new MailPresenter(this);

        codigoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.sendMail();
            }
        });

    }

    public void mailSuccessful(String randomCode) {
        Intent intent = new Intent(getApplicationContext(), AutenticationCodeActivity.class);
        intent.putExtra("randomCode", randomCode);
        intent.putExtra("useremail", mEmail.getText().toString());
        startActivity(intent);
    }



    public void mailFailure(String error) {
        Toast.makeText(getApplicationContext(), "Hubo un error: " + error, Toast.LENGTH_LONG).show();

    }



}