package com.example.truequelibre.Utils;

import com.example.truequelibre.CreateFinalizarTruequeRequest;
import com.example.truequelibre.Entity.CreateOfertaRequest;
import com.example.truequelibre.Entity.FiltrarOfertaRequest;
import com.example.truequelibre.Entity.OfertasResponse;
import com.example.truequelibre.Entity.PublicacionResponseNotificacion;
import com.example.truequelibre.Entity.UpdateOfertaVM;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IFinalizarTruequeService {

    @GET("finalizartrueque/{id}")
    Call<PublicacionResponseNotificacion> getfinalizartrueque(@Path("id") Integer id);

    @PUT("finalizartrueque/{id}")
    Call<ResponseBody> updatefinalizartrueque(@Path("id") Integer id, @Body UpdateOfertaVM request);

    @POST("finalizartrueque/")
    Call<ResponseBody> createfinalizartrueque (@Body CreateFinalizarTruequeRequest request);


}
