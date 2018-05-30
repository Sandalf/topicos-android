package com.example.topicosavanzados.topicos;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonasActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EditTextRFC,EditTextNombre,EditTextCiudad,EditTextEdad;
    Button ButtonCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditTextRFC = (EditText) findViewById(R.id.EditTextRFC);
        EditTextNombre = (EditText) findViewById(R.id.EditTextNombre);
        EditTextCiudad = (EditText) findViewById(R.id.EditTextCiudad);
        EditTextEdad = (EditText) findViewById(R.id.EditTextEdad);

        ButtonCrear = (Button) findViewById(R.id.ButtonCrear);
        ButtonCrear.setOnClickListener(this);
    }

    public void agregar() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonCrear:
                AlertDialog alerta = new AlertDialog.Builder(this).create();
                alerta.setTitle("Alerta");
                alerta.setMessage("Click");
                alerta.show();
            break;
        }
    }
}
