package com.example.topicosavanzados.topicos;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonasActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EditTextRFC,EditTextNombre,EditTextCiudad,EditTextEdad;
    Button ButtonCrear, ButtonConsultar;
    BaseDeDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new BaseDeDatos(this);

        EditTextRFC = (EditText) findViewById(R.id.EditTextRFC);
        EditTextNombre = (EditText) findViewById(R.id.EditTextNombre);
        EditTextCiudad = (EditText) findViewById(R.id.EditTextCiudad);
        EditTextEdad = (EditText) findViewById(R.id.EditTextEdad);

        ButtonCrear = (Button) findViewById(R.id.ButtonCrear);
        ButtonCrear.setOnClickListener(this);

        ButtonConsultar = (Button) findViewById(R.id.ButtonConsultar);
        ButtonConsultar.setOnClickListener(this);
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        AlertDialog alerta = new AlertDialog.Builder(this).create();
        alerta.setTitle(titulo);
        alerta.setMessage(mensaje);
        alerta.show();
    }

    public void crear() {
        db.insertarPersona(EditTextRFC.getText().toString(),
                EditTextNombre.getText().toString(),
                EditTextCiudad.getText().toString(),
                Integer.parseInt(EditTextEdad.getText().toString()));
        mostrarAlerta("Exito","Persona creada");
    }

    public void consultar() {
        Cursor res = db.obtenerPersona(EditTextRFC.getText().toString());
        if(res.getCount() == 0) {
            mostrarAlerta("Error","El registro no existe");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("RFC: "+res.getString(0)+"\n");
            buffer.append("Nombre: "+res.getString(1)+"\n");
            buffer.append("Ciudad: "+res.getString(2)+"\n");
            buffer.append("Edad: "+res.getInt(3)+"\n\n");
        }

        mostrarAlerta("Data",buffer.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonCrear:
                crear();
                break;
            case R.id.ButtonConsultar:
                consultar();
                break;
        }
    }
}
