package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.CalificacionUsuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICalificacionUsuariosService {

    @GET("calificacion/{usuario}")
    public Call<List<CalificacionUsuario>> getCalificacionesByUsuario(@Path("usuario") Integer usuario);
}
