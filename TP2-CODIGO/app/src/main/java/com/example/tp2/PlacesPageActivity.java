package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlacesPageActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_to_visit);

        listView=(ListView)findViewById(R.id.listView);
        textView=(TextView)findViewById(R.id.textView);
        listItem = getResources().getStringArray(R.array.lista_places);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedPlace=adapter.getItem(position);
                //Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

                System.out.println("valor tocado: " + selectedPlace);

                Intent intentContinuar = new Intent(getApplicationContext(), RequirementsByPlaceActivity.class);
                intentContinuar.putExtra("selectedPlace", selectedPlace);
                startActivity(intentContinuar);

            }
        });
    }
}
