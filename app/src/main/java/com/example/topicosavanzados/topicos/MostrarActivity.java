package com.example.topicosavanzados.topicos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MostrarActivity extends AppCompatActivity {

    BaseDeDatos db;
    TextView TextViewMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        TextViewMostrar = (TextView) findViewById(R.id.textView);

        db = new BaseDeDatos(this);
        mostrarDatos();
    }

    public void mostrarDatos() {
        Intent intent = getIntent();
        String tabla = intent.getStringExtra("tabla");
        Cursor res = db.obtenerTodos(tabla);
        String resultado = "";

        if(res.getCount() == 0) {
            mostrarAlerta("Lo sentimos", "No hay datos para mostrar");
            return;
        }

        if(tabla.equals("Personas")) {
            resultado = "RELACIÓN DE PERSONAS REGISTRADAS\n\n";

            // Agregar cabeceros
            resultado += (Rutinas.PonBlancos("RFC",13));
            resultado += (Rutinas.PonBlancos("NOMBRE",10));
            resultado += (Rutinas.PonBlancos("CIUDAD",10));
            resultado += (Rutinas.PonBlancos("EDAD",2)+"\n");

            // Agregar datos dfghjkl
            while(res.moveToNext()) {
                resultado += (Rutinas.PonBlancos(res.getString(0),13));
                resultado += (Rutinas.PonBlancos(res.getString(1),10));
                resultado += (Rutinas.PonBlancos(res.getString(2),10));
                resultado += (Rutinas.PonBlancos(res.getInt(3)+"",2)+"\n");
            }
        }

        TextViewMostrar.setText(resultado);
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        AlertDialog alerta = new AlertDialog.Builder(this).create();
        alerta.setTitle(titulo);
        alerta.setMessage(mensaje);
        alerta.show();
    }

}
