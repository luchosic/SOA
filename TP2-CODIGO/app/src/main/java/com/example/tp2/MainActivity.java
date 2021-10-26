package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp2.data.Battery;
import com.example.tp2.data.JavaMail;

public class MainActivity extends AppCompatActivity {

    EditText mailUser;
    public EditText mEmail;
    public String randomCode;
    Button codigoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.mailCodigo);

        codigoButton = (Button) findViewById(R.id.codigoButton);

        Battery batStatus = new Battery(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Su porcentaje de batería es: " + batStatus.getBatteryPercentage() + "%", Toast.LENGTH_LONG).show();

        codigoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches()){
                    mEmail.setError("Debe ingresar un mail válido");
                }
                else{
                    //randomCode = String.valueOf((int)(Math.random() * 9000) + 1000);
                    randomCode = String.valueOf("1234");

                    sendMail(randomCode);

                    Intent intent = new Intent(getApplicationContext(), AutenticationCodeActivity.class);
                    intent.putExtra("randomCode", randomCode);
                    intent.putExtra("useremail", mEmail.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }


    private void sendMail(String randomCode) {

        String mail = mEmail.getText().toString().trim();
        String subject = "Bienvenido a CovidLess!";
        String body = "Su código de verificación es: " + randomCode;

        //Armo y envio mail
        JavaMail javaMail = new JavaMail(this,mail,subject,body);
        javaMail.execute();

    }
}