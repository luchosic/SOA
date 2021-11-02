package com.example.tp2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.presenter.LoginPresenter;
import com.example.tp2.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {

    public MainPresenter presenter;

    ListView listView;
    TextView textView;
    String[] listItem;
    Button metricasBoton;
    String userEmail;
    Button logoutBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textBienvenida;

        textBienvenida = findViewById(R.id.textBienvenida);
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textoBienvenida);
        metricasBoton = findViewById(R.id.metricasBoton);
        logoutBoton = findViewById(R.id.logoutBoton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userEmail = extras.getString("useremail");

        textBienvenida.setText("¡Bienvenid@ " + userEmail + "!");

        presenter = new MainPresenter(this);
        presenter.iniciarHilo();

        listItem = getResources().getStringArray(R.array.lista_places);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedPlace=adapter.getItem(position);

                presenter.storePlace(selectedPlace);

                Intent intentContinuar = new Intent(getApplicationContext(), RequirementsByPlaceActivity.class);
                intentContinuar.putExtra("selectedPlace", selectedPlace);
                intentContinuar.putExtra("useremail", userEmail);
                startActivity(intentContinuar);

            }
        });

        metricasBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MetricsActivity.class);
                intent.putExtra("useremail", userEmail);
                startActivity(intent);
            }
        });

        logoutBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.detenerHilo();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("useremail", "");
                startActivity(intent);
            }
        });
    }
    
}
