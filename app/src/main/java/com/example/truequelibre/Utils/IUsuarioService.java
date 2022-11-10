package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.AuthenticationRequest;
import com.example.truequelibre.Entity.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUsuarioService {

    @POST("usuario/login/")
    Call<Usuario> authentication(@Body AuthenticationRequest request);
}
