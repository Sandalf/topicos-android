package com.example.topicosavanzados.topicos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AutosActivity extends AppCompatActivity implements View.OnClickListener{
    Button   BtnRegistrar,BtnEliminar,BtnActualizar,BtnSeleccionar,BtnMostrar,BtnLimpiar;
    EditText EditTextRFC,EditTextPlaca,EditTextPrecio;
    BaseDeDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        db = new BaseDeDatos(this);

        EditTextRFC = (EditText) findViewById(R.id.EditTextRFC_Autos);
        EditTextPlaca = (EditText) findViewById(R.id.EditTextPlaca_Autos);
        EditTextPrecio = (EditText) findViewById(R.id.EditTextPrecio_Autos);

        BtnRegistrar = (Button) findViewById(R.id.BtnRegistrar_Autos);
        BtnEliminar = (Button) findViewById(R.id.BtnEliminar_Autos);
        BtnActualizar = (Button) findViewById(R.id.BtnActualizar_Autos);
        BtnSeleccionar = (Button) findViewById(R.id.BtnConsultar_Autos);
        BtnMostrar = (Button) findViewById(R.id.BtnMostrar_Autos);
        BtnLimpiar = (Button) findViewById(R.id.BtnLimpiar_Autos);

        BtnRegistrar.setOnClickListener(this);
        BtnEliminar.setOnClickListener(this);
        BtnActualizar.setOnClickListener(this);
        BtnSeleccionar.setOnClickListener(this);
        BtnMostrar.setOnClickListener(this);
        BtnLimpiar.setOnClickListener(this);
    }

    public void crear() {
        if(validarCampos()) {
            Cursor res = db.obtenerAuto(EditTextRFC.getText().toString().trim(),EditTextPlaca.getText().toString().trim());
            if(res.getCount() > 0) {
                mostrarAlerta("Error","El registro ya existe");
                return;
            }

            Cursor resRFC = db.obtenerPersona(EditTextRFC.getText().toString().trim());
            if(resRFC.getCount() == 0) {
                mostrarAlerta("Error","El RFC no existe");
                EditTextRFC.requestFocus();
                return;
            }

            Cursor resPlaca = db.obtenerPlaca(EditTextPlaca.getText().toString().trim());
            if(resPlaca.getCount() == 0) {
                mostrarAlerta("Error","La placa no existe");
                EditTextPlaca.requestFocus();
                return;
            }

            Cursor resPlacaExistente = db.obtenerAutoPlaca(EditTextPlaca.getText().toString().trim());
            if(resPlacaExistente.getCount() >= 1) {
                mostrarAlerta("Error","Ya existe un registro con la misma placa");
                EditTextPlaca.requestFocus();
                return;
            }

            boolean resultado = db.insertarAuto(EditTextRFC.getText().toString().trim(),
                    EditTextPlaca.getText().toString().trim(),
                    Integer.parseInt(EditTextPrecio.getText().toString().trim()));
            if(resultado) {
                mostrarAlerta("Exito", "Exito al insertar");
            } else {
                mostrarAlerta("Error", "Error al insertar");
            }
            limpiar();
        }
    }

    public boolean validarCampos() {
        if (EditTextRFC.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un RFC");
            EditTextRFC.requestFocus();
            return false;
        } else if(EditTextPlaca.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar una Placa");
            EditTextPlaca.requestFocus();
            return false;
        } else if (EditTextPrecio.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un Precio");
            EditTextPrecio.requestFocus();
            return false;
        }
        return true;
    }

    public void consultar() {
        if(EditTextRFC.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un RFC");
            EditTextRFC.requestFocus();
            return;
        }

        if(EditTextPlaca.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar una Placa");
            EditTextPlaca.requestFocus();
            return;
        }

        Cursor res = db.obtenerAuto(EditTextRFC.getText().toString().trim(),EditTextPlaca.getText().toString().trim());
        if(res.getCount() == 0) {
            mostrarAlerta("Error","El registro no existe");
            return;
        }

        res.moveToFirst();
        EditTextRFC.setText(res.getString(0));
        EditTextPlaca.setText(res.getString(1));
        EditTextPrecio.setText(res.getString(2));
    }

    public void actualizar() {
        if(validarCampos()) {
            boolean resultado = db.actualizarAuto(EditTextRFC.getText().toString().trim(),
                    EditTextPlaca.getText().toString().trim(),
                    Integer.parseInt(EditTextPrecio.getText().toString().trim()));
            if(resultado) {
                mostrarAlerta("Exito", "Exito al actualizar");
            } else {
                mostrarAlerta("Error", "Error al actualizar");
            }
        }
    }

    public void eliminar() {
        if(EditTextRFC.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un RFC");
            EditTextRFC.requestFocus();
            return;
        }

        if(EditTextPlaca.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar una Placa");
            EditTextPlaca.requestFocus();
            return;
        }

        Integer filasEliminadas = db.eliminarAuto(EditTextRFC.getText().toString().trim(),EditTextPlaca.getText().toString().trim());
        if(filasEliminadas > 0) {
            mostrarAlerta("Exito", "Registro eliminado");
        } else {
            mostrarAlerta("Exito", "El registro no existe");
        }
        limpiar();
    }

    public void limpiar() {
        EditTextRFC.setText("");
        EditTextPlaca.setText("");
        EditTextPrecio.setText("");
    }

    @Override
    public void onClick(View Evt) {
        switch (Evt.getId()) {
            case R.id.BtnRegistrar_Autos:
                crear();
                break;
            case R.id.BtnConsultar_Autos:
                consultar();
                break;
            case R.id.BtnActualizar_Autos:
                actualizar();
                break;
            case R.id.BtnEliminar_Autos:
                eliminar();
                break;
            case R.id.BtnLimpiar_Autos:
                limpiar();
                break;
            case R.id.BtnMostrar_Autos:
                mostrarRegistros();
                break;
        }
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        AlertDialog alerta = new AlertDialog.Builder(this).create();
        alerta.setTitle(titulo);
        alerta.setMessage(mensaje);
        alerta.show();
    }

    public void mostrarRegistros() {
        Intent intent = new Intent(this, MostrarActivity.class);
        intent.putExtra("tabla", "Autos");
        startActivity(intent);
    }
}
