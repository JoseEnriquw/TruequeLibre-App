package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.Publicacion;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPublicacionService {

    @GET("publicacion/")
    Call<List<Publicacion>> getPublicaciones();

    @DELETE("publicacion/{id}")
    Call<ResponseBody> deletePublicacion(@Path("id") Integer id);

    @GET("publicacion/cargarDropdown")
    Call<PublicacionDropdown> getPublicacionDropdown();
}
