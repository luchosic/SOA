package com.example.tp2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textBienvenida;

        textBienvenida = findViewById(R.id.textBienvenida);
        listView=(ListView)findViewById(R.id.listView);
        textView=(TextView)findViewById(R.id.textoBienvenida);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String userEmail = extras.getString("useremail");

        textBienvenida.setText("¡Bienvenid@ " + userEmail.split("@")[0].trim() + "!");

        listItem = getResources().getStringArray(R.array.lista_places);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedPlace=adapter.getItem(position);

                Intent intentContinuar = new Intent(getApplicationContext(), RequirementsByPlaceActivity.class);
                intentContinuar.putExtra("selectedPlace", selectedPlace);
                startActivity(intentContinuar);

            }
        });
    }
}