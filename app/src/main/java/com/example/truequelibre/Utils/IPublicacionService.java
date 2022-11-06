package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.EPublicaciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPublicacionService {

    @GET("publicacion/")
    Call<List<EPublicaciones>> getPublicaciones();
}
