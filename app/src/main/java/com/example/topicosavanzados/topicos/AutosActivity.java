package com.example.topicosavanzados.topicos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AutosActivity extends Activity implements View.OnClickListener{
    Button   BtnRegistrar,BtnEliminar,BtnActualizar,BtnSeleccionar,BtnMostrar;
    EditText editTextRFC,editTextPlaca,editTextPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);
        editTextRFC = (EditText) findViewById(R.id.EditTextRFC_Autos);
        editTextPlaca = (EditText) findViewById(R.id.EditTextPlaca_Autos);
        editTextPrecio = (EditText) findViewById(R.id.EditTextPrecio_Autos);

        BtnRegistrar = (Button) findViewById(R.id.BtnRegistrar_Autos);
        BtnEliminar = (Button) findViewById(R.id.BtnEliminar_Autos);
        BtnActualizar = (Button) findViewById(R.id.BtnActualizar_Autos);
        BtnSeleccionar = (Button) findViewById(R.id.BtnSeleccionar_Autos);
        BtnMostrar = (Button) findViewById(R.id.BtnMostrar_Autos);

        BtnRegistrar.setOnClickListener(this);
        BtnEliminar.setOnClickListener(this);
        BtnActualizar.setOnClickListener(this);
        BtnSeleccionar.setOnClickListener(this);
        BtnMostrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View Evt) {
        if(Evt == BtnRegistrar){

        }

        if(Evt == BtnEliminar){

        }

        if(Evt == BtnActualizar){

        }

        if(Evt == BtnSeleccionar){

        }

        if(Evt == BtnMostrar){

        }

    }
}
