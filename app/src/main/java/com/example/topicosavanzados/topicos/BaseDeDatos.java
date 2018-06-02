package com.example.topicosavanzados.topicos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luis on 5/29/18.
 */

public class BaseDeDatos extends SQLiteOpenHelper {

    final static int Version = 0;
    public static final String NOMBRE_DB = "Automoviles.db";
    public static final String TABLA_PERSONAS = "CREATE TABLE Personas (RFC TEXT PRIMARY KEY, Nombre TEXT, Ciudad TEXT, Edad INTEGER)";

    public BaseDeDatos(Context contexto) {
        super(contexto, NOMBRE_DB, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_PERSONAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Personas");
        onCreate(db);
    }

    public boolean insertarPersona(String rfc, String nombre, String ciudad, int edad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RFC",rfc);
        contentValues.put("Nombre",nombre);
        contentValues.put("Ciudad",ciudad);
        contentValues.put("Edad",edad);
        long result = db.insert("Personas",null,contentValues);
        if(result == -1)
            return false;
        else
            return  true;
    }

    public Cursor obtenerPersona(String rfc) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Personas WHERE RFC = '?'",new String[]{rfc});
        return res;
    }

    public boolean actualizarPersona(String rfc, String nombre, String ciudad, int edad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RFC",rfc);
        contentValues.put("Nombre",nombre);
        contentValues.put("Ciudad",ciudad);
        contentValues.put("Edad",edad);
        long result = db.update("Personas",contentValues,"RFC = ?",new String[]{rfc});
        if(result == -1)
            return false;
        else
            return  true;
    }

    public Integer eliminarPersona(String rfc) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Personas","RFC = ?", new String[]{rfc});
    }

    public Cursor obtenerTodos(String tabla) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tabla,null);
        return res;
    }

}
