package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.GetAllByCategoriaFilterRequest;
import com.example.truequelibre.Entity.GetAllByCategoriaRequest;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.PublicacionCreateRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IPublicacionService {

    @GET("publicacion/{usuario}")
    Call<List<Publicacion>> getPublicaciones(@Path("usuario")Integer usuario);

    @DELETE("publicacion/{id}")
    Call<ResponseBody> deletePublicacion(@Path("id") Integer id);

    @GET("publicacion/cargarDropdown")
    Call<PublicacionDropdown> getPublicacionDropdown();

    @POST("publicacion/")
    Call<Void>create(@Body PublicacionCreateRequest request);

    @POST("publicacion/categoria/")
    Call<List<Publicacion>> getPublicacionesByCategoria(@Body GetAllByCategoriaRequest request);

    @POST("publicacion/filtrar/")
    Call<List<Publicacion>> getAllByCategoriaFilter(@Body GetAllByCategoriaFilterRequest request);
}
