package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tp2.data.InternetConnection;
import com.google.android.material.snackbar.Snackbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class RegisterActivity extends AppCompatActivity {

    private InternetConnection internetConnection;
    LinearLayout layoutWidgetNoInternetConnection;

    Button volverBtn;
    Button registrateButtom;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(InternetConnection.isOnline(this)){
            Log.e(TAG, "Connected to Internet");
        } else
            Log.e(TAG, "NOT Connected to Internet");

        volverBtn = (Button)findViewById(R.id.volverButton);

        volverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registrateButtom = (Button)findViewById(R.id.registrateButtom);

        registrateButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //aca iria el código de cuando apretas REGISTRATE.
                Log.e(TAG, "loguando");
            }
        });




    }


}