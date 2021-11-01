package com.example.tp2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;

public class FinalActivity extends AppCompatActivity {
    public Button volverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        volverButton = (Button)findViewById(R.id.volverButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String userEmail = extras.getString("useremail");

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(getApplicationContext(), MainActivity.class);
                intentVolver.putExtra("useremail", userEmail);
                startActivity(intentVolver);
            }
        });

    }
}
