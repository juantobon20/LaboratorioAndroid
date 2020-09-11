package com.appinc.laboratorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.appinc.laboratorio.Database.SqliteDatabase;
import com.appinc.laboratorio.Model.Persona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private ListView listNombre;

    private SqliteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new SqliteDatabase(this);

        txtNombre = findViewById(R.id.txtNombre);
        listNombre = findViewById(R.id.listNombre);

        this.Mostrar();
    }

    public void Mostrar(){
        List<Persona> personas =database.GetPersonas();
        if (personas.size() == 0){
            Toast.makeText(this, "No hay personas registradas", Toast.LENGTH_LONG).show();
            return;
        }

        List<String> nombres = new ArrayList<>();
        for (Persona persona: personas){
            nombres.add(persona.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);
        listNombre.setAdapter(adapter);
    }

    public void guardar(View view) {
        ContentValues values = new ContentValues();
        values.put("Nombre", txtNombre.getText().toString());

        if (database.InsertPerson(values) == -1){
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();
        txtNombre.setText("");
        Mostrar();
    }
}