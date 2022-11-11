package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.Dropdown.UsuarioDropdown;

import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Entity.UsuarioCreateRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import java.util.List;
import com.example.truequelibre.Entity.AuthenticationRequest;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface IUsuarioService {
    @GET("usuario/")
    Call<List<Usuario>> getUsuario();

    @GET("usuario/cargarDropdown")
    Call<UsuarioDropdown> getUsuarioDropdown();

    @POST("usuario/")
    Call<Void>create(@Body UsuarioCreateRequest request);

    @POST("usuario/login/")
    Call<Integer> authentication(@Body AuthenticationRequest request);

    @GET("usuario/getOne/{id}")
    Call<Usuario> getById(@Path("id") Integer id);
}
