package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.CalificacionUsuario;
import com.example.truequelibre.Entity.CreateCalificacionRequest;
import com.example.truequelibre.Entity.CreateOfertaRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICalificacionUsuariosService {

    @GET("calificacion/{usuario}")
    public Call<List<CalificacionUsuario>> getCalificacionesByUsuario(@Path("usuario") Integer usuario);

    @POST("calificacion/")
    Call<ResponseBody> createCalificacion (@Body CreateCalificacionRequest request);
}
