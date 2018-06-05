package com.example.topicosavanzados.topicos;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReportesActivity extends AppCompatActivity implements View.OnClickListener {

    Button ButtonReporte2, ButtonReporte3;
    BaseDeDatos db;
    TextView TextViewReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        db = new BaseDeDatos(this);

        TextViewReportes = (TextView) findViewById(R.id.TextViewReporte);

        ButtonReporte2 = (Button) findViewById(R.id.ButtonReporte_2);
        ButtonReporte2.setOnClickListener(this);

        ButtonReporte3 = (Button) findViewById(R.id.ButtonReporte_3);
        ButtonReporte3.setOnClickListener(this);
    }

    @Override
    public void onClick(View Evt) {
        switch (Evt.getId()) {
            case R.id.ButtonReporte_2:
                mostrarReporte2();
                break;
            case R.id.ButtonReporte_3:
                mostrarReporte3();
                break;
        }
    }

    public void mostrarReporte2() {
        Cursor res = db.reporte2();
        String resultado = "";

        resultado = "IMPORTES POR CIUDAD-MODELO\n\n";

        // Agregar cabeceros
        resultado += (Rutinas.PonBlancos("CIUDAD",13));
        resultado += (Rutinas.PonBlancos("MODELO",8));
        resultado += (Rutinas.PonBlancos("IMPORTE",8)+"\n");

        // Agregar datos
        while(res.moveToNext()) {
            resultado += (Rutinas.PonBlancos(res.getString(0),13));
            resultado += (Rutinas.PonBlancos(res.getInt(1)+"",8));
            resultado += (Rutinas.PonBlancos(res.getInt(2)+"",8)+"\n");
        }

        TextViewReportes.setText(resultado);
    }

    public void mostrarReporte3() {
        Cursor res = db.reporte3();
        String resultado = "";

        resultado = "CONCENTRADO DE AUTOS POR PERSONAS\n\n";

        // Agregar cabeceros
        resultado += (Rutinas.PonBlancos("RFC",13));
        resultado += (Rutinas.PonBlancos("NUM",5));
        resultado += (Rutinas.PonBlancos("MAX",5));
        resultado += (Rutinas.PonBlancos("MIN",5));
        resultado += (Rutinas.PonBlancos("AVG",5)+"\n");

        // Agregar datos
        while(res.moveToNext()) {
            resultado += (Rutinas.PonBlancos(res.getString(0),13));
            resultado += (Rutinas.PonBlancos(res.getInt(1)+"",5));
            resultado += (Rutinas.PonBlancos(res.getInt(2)+"",5));
            resultado += (Rutinas.PonBlancos(res.getInt(3)+"",5));
            resultado += (Rutinas.PonBlancos(res.getInt(4)+"",5)+"\n");
        }

        TextViewReportes.setText(resultado);
    }
}
