package com.appinc.laboratorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.appinc.laboratorio.Controller.PersonaController;
import com.appinc.laboratorio.Database.SqliteDatabase;
import com.appinc.laboratorio.Model.Persona;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText txtNombre;
    private ListView listNombre;

    private List<Persona> personas;
    private Persona persona;

    private SqliteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new SqliteDatabase(this);

        txtNombre = findViewById(R.id.txtNombre);
        listNombre = findViewById(R.id.listNombre);
        listNombre.setOnItemClickListener(this);

        this.Mostrar();
    }

    public void Mostrar() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String error = null;

                try {
                    personas = new PersonaController().GetAsync();
                } catch (IOException e) {
                    error = e.getMessage();
                }

                final String finalError = error;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (finalError != null) {
                            Toast.makeText(MainActivity.this, finalError, Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (personas == null || personas.size() == 0) {
                            Toast.makeText(MainActivity.this, "No hay registros", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<String> nombres = new ArrayList<>();
                        for (Persona persona : personas) {
                            nombres.add(persona.toString());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, nombres);
                        listNombre.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    /*public void Mostrar(){
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
    }*/

    public void guardar(View view) {
        final Persona person = new Persona();
        person.setNombre(txtNombre.getText().toString());
        person.setApellido("Prueba");
        person.setTelefono("123456789");

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String error = null;

                try {
                    persona = new PersonaController().InsertAsync(person);
                } catch (IOException e) {
                    error = e.getMessage();
                }

                final String finalError = error;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (finalError != null) {
                            Toast.makeText(MainActivity.this, finalError, Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (persona == null) {
                            Toast.makeText(MainActivity.this, "Error al registrar", Toast.LENGTH_LONG).show();
                            return;
                        }

                        txtNombre.setText("");
                        Mostrar();
                    }
                });
            }
        }).start();
       /* ContentValues values = new ContentValues();
        values.put("Nombre", txtNombre.getText().toString());

        if (database.InsertPerson(values) == -1) {
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, personas.get(i).toString(), Toast.LENGTH_LONG).show();
    }
}