package com.example.topicosavanzados.topicos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlacasActivity extends Activity implements View.OnClickListener{

    EditText EditTextPlaca,EditTextMarca,EditTextLinea,EditTextModelo;
    Button BtnCrear,BtnConsultar,BtnActualizar,BtnEliminar,BtnMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placas);

        EditTextPlaca = (EditText) findViewById(R.id.EditTextPlaca_Placas);
        EditTextMarca = (EditText) findViewById(R.id.EditTextMarca_Placas);
        EditTextLinea = (EditText) findViewById(R.id.EditTextLinea_Placas);
        EditTextModelo = (EditText) findViewById(R.id.EditTextModelo_Placas);

        BtnCrear = (Button) findViewById(R.id.BtnCrear_Placas);
        BtnConsultar = (Button) findViewById(R.id.BtnConsultar_Placas);
        BtnActualizar = (Button) findViewById(R.id.BtnActualizar_Placas);
        BtnEliminar = (Button) findViewById(R.id.BtnEliminar_Placas);
        BtnMostrar = (Button) findViewById(R.id.BtnMostrar_Placas);

        BtnCrear.setOnClickListener(this);
        BtnConsultar.setOnClickListener(this);
        BtnActualizar.setOnClickListener(this);
        BtnEliminar.setOnClickListener(this);
        BtnMostrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View Evt) {


        if(Evt == BtnCrear){

        }

        if(Evt == BtnConsultar){

        }

        if(Evt == BtnActualizar){

        }

        if(Evt == BtnEliminar){

        }

        if(Evt == BtnMostrar){

        }
    }
}
