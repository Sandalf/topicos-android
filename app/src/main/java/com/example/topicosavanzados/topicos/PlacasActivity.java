package com.example.topicosavanzados.topicos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlacasActivity extends Activity implements View.OnClickListener{

    EditText EditTextPlaca,EditTextMarca,EditTextLinea,EditTextModelo;
    Button BtnCrear,BtnConsultar,BtnActualizar,BtnEliminar,BtnMostrar,BtnLimpiar;
    BaseDeDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placas);

        db = new BaseDeDatos(this);

        EditTextPlaca = (EditText) findViewById(R.id.EditTextPlaca_Placas);
        EditTextMarca = (EditText) findViewById(R.id.EditTextMarca_Placas);
        EditTextLinea = (EditText) findViewById(R.id.EditTextLinea_Placas);
        EditTextModelo = (EditText) findViewById(R.id.EditTextModelo_Placas);

        BtnCrear = (Button) findViewById(R.id.BtnCrear_Placas);
        BtnConsultar = (Button) findViewById(R.id.BtnConsultar_Placas);
        BtnActualizar = (Button) findViewById(R.id.BtnActualizar_Placas);
        BtnEliminar = (Button) findViewById(R.id.BtnEliminar_Placas);
        BtnMostrar = (Button) findViewById(R.id.BtnMostrar_Placas);
        BtnLimpiar = (Button) findViewById(R.id.BtnLimpiar_Placas);

        BtnCrear.setOnClickListener(this);
        BtnConsultar.setOnClickListener(this);
        BtnActualizar.setOnClickListener(this);
        BtnEliminar.setOnClickListener(this);
        BtnMostrar.setOnClickListener(this);
        BtnLimpiar.setOnClickListener(this);
    }

    public void crear() {
        if(validarCampos()) {
            boolean resultado = db.insertaPlaca(EditTextPlaca.getText().toString().trim(),
                    EditTextMarca.getText().toString().trim(),
                    EditTextLinea.getText().toString().trim(),
                    Integer.parseInt(EditTextModelo.getText().toString().trim()));
            if(resultado) {
                mostrarAlerta("Exito", "Exito al insertar");
            } else {
                mostrarAlerta("Error", "Error al insertar");
            }
        }
    }

    public boolean validarCampos() {
        if(EditTextPlaca.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar la Placa");
            EditTextPlaca.requestFocus();
            return false;
        } else if (EditTextMarca.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar una Marca");
            EditTextMarca.requestFocus();
            return false;
        } else if (EditTextLinea.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un tipo de Linea");
            EditTextLinea.requestFocus();
            return false;
        } else if (EditTextModelo.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un Modelo");
            EditTextModelo.requestFocus();
            return false;
        }
        return true;
    }

    public void consultar() {
        if(EditTextPlaca.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un RFC");
            EditTextPlaca.requestFocus();
            return;
        }

        Cursor res = db.obtenerPlaca(EditTextPlaca.getText().toString().trim());
        if(res.getCount() == 0) {
            mostrarAlerta("Error","El registro no existe");
            return;
        }

        res.moveToFirst();
        EditTextPlaca.setText(res.getString(0));
        EditTextMarca.setText(res.getString(1));
        EditTextLinea.setText(res.getString(2));
        EditTextModelo.setText(res.getString(3));
    }

    public void actualizar() {
        if(validarCampos()) {
            boolean resultado = db.actualizarPlaca(EditTextPlaca.getText().toString().trim(),
                    EditTextMarca.getText().toString().trim(),
                    EditTextLinea.getText().toString().trim(),
                    Integer.parseInt(EditTextModelo.getText().toString().trim()));
            if(resultado) {
                mostrarAlerta("Exito", "Exito al actualizar");
            } else {
                mostrarAlerta("Error", "Error al actualizar");
            }
        }
    }

    public void eliminar() {
        if(EditTextPlaca.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar una placa");
            EditTextPlaca.requestFocus();
            return;
        }

        Integer filasEliminadas = db.eliminarPlaca(EditTextPlaca.getText().toString().trim());
        if(filasEliminadas > 0) {
            mostrarAlerta("Exito", "Registro eliminado");
        } else {
            mostrarAlerta("Exito", "El registro no existe");
        }
        limpiar();
    }

    public void limpiar() {
        EditTextPlaca.setText("");
        EditTextMarca.setText("");
        EditTextLinea.setText("");
        EditTextModelo.setText("");
    }

    @Override
    public void onClick(View Evt) {
        switch (Evt.getId()) {
            case R.id.BtnCrear_Placas:
                crear();
                break;
            case R.id.BtnConsultar_Placas:
                consultar();
                break;
            case R.id.BtnActualizar_Placas:
                actualizar();
                break;
            case R.id.BtnEliminar_Placas:
                eliminar();
                break;
            case R.id.BtnLimpiar_Placas:
                limpiar();
                break;
            case R.id.BtnMostrar_Placas:
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
        intent.putExtra("tabla", "Placas");
        startActivity(intent);
    }

}
