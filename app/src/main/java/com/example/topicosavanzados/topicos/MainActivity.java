package com.example.topicosavanzados.topicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    BaseDeDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new BaseDeDatos(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_personas:
                Intent personasIntent = new Intent(this,PersonasActivity.class);
                this.startActivity(personasIntent);
                return true;
            case R.id.item_placas:
                Intent placasIntent = new Intent(this,PlacasActivity.class );
                this.startActivity(placasIntent);
                return true;
            case R.id.item_autos:
                Intent autosIntent = new Intent(this,AutosActivity.class);
                this.startActivity(autosIntent);
                return true;
            case R.id.item_reportes:
                Intent reportesIntent = new Intent(this,ReportesActivity.class);
                this.startActivity(reportesIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
