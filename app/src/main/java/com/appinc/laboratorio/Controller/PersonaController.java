package com.appinc.laboratorio.Controller;

import com.appinc.laboratorio.Model.Persona;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PersonaController {

    private Connection connect = new Connection();

    public Persona GetAsync(int id) throws IOException {
        Call<Persona> call = connect.IPersona().GetAsync(id);
        Response<Persona> response = call.execute();
        return response.body();
    }

    public List<Persona> GetAsync() throws IOException {
        Call<List<Persona>> call = connect.IPersona().GetAsync();
        Response<List<Persona>> response = call.execute();
        return response.body();
    }

    public Persona InsertAsync(Persona persona) throws IOException {
        Call<Persona> call = connect.IPersona().InsertAsync(persona);
        Response<Persona> response = call.execute();
        return response.body();
    }

    public Persona UpdateAsync(int id, Persona persona) throws IOException {
        Call<Persona> call = connect.IPersona().UpdateAsync(id, persona);
        Response<Persona> response = call.execute();
        return response.body();
    }

    public Boolean DeleteAsync(int id) throws IOException {
        Call<Boolean> call = connect.IPersona().DeleteAsync(id);
        Response<Boolean> response = call.execute();
        return response.body();
    }
}