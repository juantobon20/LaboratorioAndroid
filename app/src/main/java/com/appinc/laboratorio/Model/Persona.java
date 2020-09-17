package com.appinc.laboratorio.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Persona {

    @SerializedName("id")
    private int Id;
    @SerializedName("nombre")
    private String Nombre;
    @SerializedName("apellido")
    private String Apellido;
    @SerializedName("telefono")
    private String Telefono;

    public Persona() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    @NonNull
    @Override
    public String toString() {
        return "Id= " + Id + " Nombre= " + Nombre + " Apellido= " + Apellido + " Telefono= " + Telefono;
    }
}