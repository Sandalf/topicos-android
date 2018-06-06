package com.example.topicosavanzados.topicos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDeDatos extends SQLiteOpenHelper {

    final static int Version = 3;
    public static final String NOMBRE_DB = "Automoviles.db";
    public static final String TABLA_PERSONAS = "CREATE TABLE Personas (RFC TEXT PRIMARY KEY, Nombre TEXT, Ciudad TEXT, Edad INTEGER)";
    public static final String TABLA_PLACAS = "CREATE TABLE Placas (Placa TEXT PRIMARY KEY, Marca TEXT, Linea TEXT, Modelo INTEGER)";
    public static final String TABLA_AUTOS = "CREATE TABLE Autos (RFC TEXT, Placa TEXT, Precio REAL, FOREIGN KEY(RFC) REFERENCES Personas(RFC), FOREIGN KEY(Placa) REFERENCES Personas(Placa))";

    public BaseDeDatos(Context contexto) {
        super(contexto, NOMBRE_DB, null, Version);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_PERSONAS);
        db.execSQL(TABLA_PLACAS);
        db.execSQL(TABLA_AUTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Personas");
        db.execSQL("DROP TABLE IF EXISTS Placas");
        db.execSQL("DROP TABLE IF EXISTS Autos");
        onCreate(db);
    }

    //-------------------------------------------------------------------------------------//

    public Cursor obtenerTodos(String tabla) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tabla, null);
        return res;
    }

    public boolean insertaPlaca(String placa, String marca, String linea, int modelo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Placa", placa);
        contentValues.put("Marca", marca);
        contentValues.put("Linea", linea);
        contentValues.put("Modelo", modelo);
        long result = db.insert("Placas", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor obtenerPlaca(String placa) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("Placas",new String[]{"Placa","Marca","Linea","Modelo"},"Placa = ?",new String[]{placa},null,null,null,"1");
        return res;
    }

    public boolean actualizarPlaca(String placa, String marca, String linea, int modelo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Placa", placa);
        contentValues.put("Marca", marca);
        contentValues.put("Linea", linea);
        contentValues.put("Modelo", modelo);
        long result = db.update("Placas", contentValues, "Placa = ?", new String[]{placa});
        if (result == -1)
            return false;
        else
            return true;
    }

    public Integer eliminarPlaca(String placa) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Placas", "Placa = ?", new String[]{placa});
    }

    //-------------------------------------------------------------------------------------//

    public boolean insertarPersona(String rfc, String nombre, String ciudad, int edad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RFC", rfc);
        contentValues.put("Nombre", nombre);
        contentValues.put("Ciudad", ciudad);
        contentValues.put("Edad", edad);
        long result = db.insert("Personas", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor obtenerPersona(String rfc) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("Personas",new String[]{"RFC","Nombre","Ciudad","Edad"},"RFC = ?",new String[]{rfc},null,null,null,"1");
        return res;
    }

    public boolean actualizarPersona(String rfc, String nombre, String ciudad, int edad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RFC", rfc);
        contentValues.put("Nombre", nombre);
        contentValues.put("Ciudad", ciudad);
        contentValues.put("Edad", edad);
        long result = db.update("Personas", contentValues, "RFC = ?", new String[]{rfc});
        if (result == -1)
            return false;
        else
            return true;
    }

    public Integer eliminarPersona(String rfc) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Personas", "RFC = ?", new String[]{rfc});
    }

    //-------------------------------------------------------------------------------------//

    public boolean insertarAuto(String rfc, String placa, double precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RFC", rfc);
        contentValues.put("Placa", placa);
        contentValues.put("Precio", precio);
        long result = db.insert("Autos", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor obtenerAuto(String rfc, String placa) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("Autos",new String[]{"RFC","Placa","Precio"},"RFC = ? AND Placa = ?",new String[]{rfc,placa},null,null,null,"1");
        return res;
    }

    public boolean actualizarAuto(String rfc, String placa, double precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RFC", rfc);
        contentValues.put("Placa", placa);
        contentValues.put("Precio", precio);
        long result = db.update("Autos", contentValues, "RFC = ? AND Placa = ?", new String[]{rfc,placa});
        if (result == -1)
            return false;
        else
            return true;
    }

    public Integer eliminarAuto(String rfc, String placa) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Autos", "RFC = ? AND Placa = ?", new String[]{rfc,placa});
    }

    //-------------------------------REPORTES-------------------------------------------//
    public Cursor reporte1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(
                "SELECT P.RFC,P.Nombre, Pl.Marca, Pl.Modelo, Pl.Placa " +
                        "FROM Personas P " +
                        "INNER JOIN Autos A ON A.RFC = P.RFC "+
                        "INNER JOIN Placas Pl ON Pl.Placa = A.Placa "+
                        "GROUP BY P.RFC,P.Nombre, Pl.Marca, Pl.Modelo , Pl.Placa",null);
        return res;
    }
    public Cursor reporte2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(
                "SELECT P.Ciudad, Pl.Modelo, SUM(A.Precio)" +
                        "FROM Personas P " +
                        "INNER JOIN Autos A ON A.RFC = P.RFC " +
                        "INNER JOIN Placas Pl ON Pl.Placa = A.Placa " +
                        "GROUP BY P.Ciudad, Pl.Modelo",null);
        return res;
    }

    public Cursor reporte3() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(
                "SELECT P.RFC, COUNT(*), MAX(Pl.Modelo), MIN(Pl.Modelo), AVG(Pl.Modelo) " +
                "FROM Personas P " +
                "INNER JOIN Autos A ON A.RFC = P.RFC " +
                "INNER JOIN Placas Pl ON Pl.Placa = A.Placa " +
                "GROUP BY P.RFC",null);
        return res;
    }

    public Cursor reporte4(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(
        "SELECT P.Ciudad, COUNT(*), MIN(Pl.Modelo), MAX(Pl.Modelo), AVG(Pl.Modelo) " +
                "FROM Personas P " +
                "INNER JOIN Autos A ON A.RFC = P.RFC " +
                "INNER JOIN Placas Pl ON Pl.Placa = A.Placa " +
                "GROUP BY P.Ciudad",null);
        return res;
    }

}
