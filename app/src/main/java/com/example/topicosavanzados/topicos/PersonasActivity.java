package com.example.topicosavanzados.topicos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonasActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EditTextRFC,EditTextNombre,EditTextCiudad,EditTextEdad;
    Button ButtonCrear, ButtonConsultar, ButtonActualizar, ButtonEliminar, ButtonLimpiar, ButtonMostrar;
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

        ButtonActualizar = (Button) findViewById(R.id.ButtonActualizar);
        ButtonActualizar.setOnClickListener(this);

        ButtonEliminar = (Button) findViewById(R.id.ButtonEliminar);
        ButtonEliminar.setOnClickListener(this);
        //hola
        ButtonLimpiar = (Button) findViewById(R.id.ButtonLimpiar);
        ButtonLimpiar.setOnClickListener(this);

        ButtonMostrar = (Button) findViewById(R.id.ButtonMostrar);
        ButtonMostrar.setOnClickListener(this);
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        AlertDialog alerta = new AlertDialog.Builder(this).create();
        alerta.setTitle(titulo);
        alerta.setMessage(mensaje);
        alerta.show();
    }

    public void crear() {
        if(validarCampos()) {
            boolean resultado = db.insertarPersona(EditTextRFC.getText().toString().trim(),
                    EditTextNombre.getText().toString().trim(),
                    EditTextCiudad.getText().toString().trim(),
                    Integer.parseInt(EditTextEdad.getText().toString().trim()));
            if(resultado) {
                mostrarAlerta("Exito", "Exito al insertar");
            } else {
                mostrarAlerta("Error", "Error al insertar");
            }
        }
    }

    public boolean validarCampos() {
        if(EditTextRFC.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un RFC");
            EditTextRFC.requestFocus();
            return false;
        } else if (EditTextNombre.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar un Nombre");
            EditTextNombre.requestFocus();
            return false;
        } else if (EditTextCiudad.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar una Ciudad");
            EditTextCiudad.requestFocus();
            return false;
        } else if (EditTextEdad.getText().toString().trim().length() == 0) {
            mostrarAlerta("Error","Debe insertar una Edad");
            EditTextEdad.requestFocus();
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

        Cursor res = db.obtenerPersona(EditTextRFC.getText().toString().trim());
        if(res.getCount() == 0) {
            mostrarAlerta("Error","El registro no existe");
            return;
        }

        res.moveToFirst();
        EditTextRFC.setText(res.getString(0));
        EditTextNombre.setText(res.getString(1));
        EditTextCiudad.setText(res.getString(2));
        EditTextEdad.setText(res.getString(3));
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
            case R.id.ButtonActualizar:
                actualizar();
                break;
            case R.id.ButtonEliminar:
                eliminar();
                break;
            case R.id.ButtonLimpiar:
                limpiar();
                break;
            case R.id.ButtonMostrar:
                mostrarRegistros();
                break;
        }
    }

    public void actualizar() {
        if(validarCampos()) {
            boolean resultado = db.actualizarPersona(EditTextRFC.getText().toString().trim(),
                    EditTextNombre.getText().toString().trim(),
                    EditTextCiudad.getText().toString().trim(),
                    Integer.parseInt(EditTextEdad.getText().toString().trim()));
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

        Integer filasEliminadas = db.eliminarPersona(EditTextRFC.getText().toString().trim());
        if(filasEliminadas > 0) {
            mostrarAlerta("Exito", "Registro eliminado");
        } else {
            mostrarAlerta("Exito", "El registro no existe");
        }
        limpiar();
    }

    public void limpiar() {
        EditTextRFC.setText("");
        EditTextNombre.setText("");
        EditTextCiudad.setText("");
        EditTextEdad.setText("");
    }

    public void mostrarRegistros() {
        Intent intent = new Intent(this, MostrarActivity.class);
        intent.putExtra("tabla", "Personas");
        startActivity(intent);
    }
}
