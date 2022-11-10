package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.Dropdown.UsuarioDropdown;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.PublicacionCreateRequest;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Entity.UsuarioCreateRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUsuarioService {
    @GET("usuario/")
    Call<List<Usuario>> getUsuario();

    @GET("usuario/cargarDropdown")
    Call<UsuarioDropdown> getUsuarioDropdown();

    @POST("usuario/")
    Call<Void>create(@Body UsuarioCreateRequest request);
}
