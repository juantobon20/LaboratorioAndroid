package com.appinc.laboratorio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.appinc.laboratorio.Model.Persona;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase extends SQLiteOpenHelper {

    public SqliteDatabase(@Nullable Context context) {
        super(context, "Coco.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Scripts.Persona);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("ALTER TABLE PERSONA ADD COLUMN Telefono VARCHAR(10)");
    }

    public long InsertPerson(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("Persona", null, values);
    }

    public List<Persona> GetPersonas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Persona> rpta = new ArrayList<>();

        String query = "SELECT * FROM PERSONA";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Persona persona = new Persona();
                persona.setId(cursor.getInt(0));
                persona.setNombre(cursor.getString(1));
                rpta.add(persona);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rpta;
    }
}