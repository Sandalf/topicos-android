package com.example.topicosavanzados.topicos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReportesActivity extends AppCompatActivity  {
    BaseDeDatos db;
    TextView TextViewReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);
        db = new BaseDeDatos(this);
        TextViewReportes = (TextView) findViewById(R.id.TextViewReporte);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reportes_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_reporte1:
                mostrarReporte1();
                return true;
            case R.id.item_reporte2:
                mostrarReporte2();
                return true;
            case R.id.item_reporte3:
                mostrarReporte3();
                return true;
            case R.id.item_reporte4:
                mostrarReporte4();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void mostrarReporte1(){
        Cursor res = db.reporte1();
        String resultado = "";

        resultado = "RELACIÓN PERSONAS CON AUTOS\n\n";

        //Agregar cabeceros
        resultado += (Rutinas.PonBlancos("RFC",13));
        resultado += (Rutinas.PonBlancos("NOMBRE",13));
        resultado += (Rutinas.PonBlancos("MARCA",8));
        resultado += (Rutinas.PonBlancos("MODELO",8));
        resultado += (Rutinas.PonBlancos("PLACA",13)+"\n");

        //Agregar datos
        while (res.moveToNext()){
            resultado += (Rutinas.PonBlancos(res.getString(0),13));
            resultado += (Rutinas.PonBlancos(res.getString(1),13));
            resultado += (Rutinas.PonBlancos(res.getString(2),8));
            resultado += (Rutinas.PonBlancos(res.getInt(3)+"",8));
            resultado += (Rutinas.PonBlancos(res.getString(4),13)+"\n");
        }
        TextViewReportes.setText(resultado);
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

    public void mostrarReporte4(){
        Cursor res = db.reporte4();
        String resultado = "";

        resultado = "CONCENTRADO DE AUTOS POR CIUDAD\n\n";

        //Agregar cabeceros
        resultado += (Rutinas.PonBlancos("CIUDAD",13));
        resultado += (Rutinas.PonBlancos("NO.AUTOS",13));
        resultado += (Rutinas.PonBlancos("MODELO_V",8));
        resultado += (Rutinas.PonBlancos("MODELO_N",8));
        resultado += (Rutinas.PonBlancos("MODELO_P",13)+"\n");

        //Agregar datos
        while (res.moveToNext()){
            resultado += (Rutinas.PonBlancos(res.getString(0),13));
            resultado += (Rutinas.PonBlancos(res.getInt(1)+"",13));
            resultado += (Rutinas.PonBlancos(res.getInt(2)+"",8));
            resultado += (Rutinas.PonBlancos(res.getInt(3)+"",8));
            resultado += (Rutinas.PonBlancos(res.getInt(4)+"",13)+"\n");
        }
        TextViewReportes.setText(resultado);
    }
}
