package com.appinc.laboratorio.Interface;

import com.appinc.laboratorio.Model.Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPersona {

    @GET("Persona")
    Call<List<Persona>> GetAsync();

    @GET("Persona/{id}")
    Call<Persona> GetAsync(@Path("id") int id);

    @POST("Persona")
    Call<Persona> InsertAsync(@Body Persona persona);

    @PUT("Persona/{id}")
    Call<Persona> UpdateAsync(@Path("id") int id, @Body Persona persona);
}