package com.example.topicosavanzados.topicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PersonasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}