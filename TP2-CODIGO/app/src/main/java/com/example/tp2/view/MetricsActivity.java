package com.example.tp2.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.presenter.MainPresenter;
import com.example.tp2.presenter.MetricsPresenter;

public class MetricsActivity extends AppCompatActivity {

    public MetricsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);

        TextView loguinByHour;
        TextView placesMostVisited;

        presenter = new MetricsPresenter(this);

    }
}
