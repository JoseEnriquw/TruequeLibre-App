package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.CreateOfertaRequest;
import com.example.truequelibre.Entity.FiltrarOfertaRequest;
import com.example.truequelibre.Entity.FinalizarTrueque;
import com.example.truequelibre.Entity.OfertasResponse;
import com.example.truequelibre.Entity.PublicacionResponseNotificacion;
import com.example.truequelibre.Entity.UpdateComentarVM;
import com.example.truequelibre.Entity.UpdateFinalizarVM;
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

public interface IOfertaService {

    @GET("oferta/{id}")
    Call<PublicacionResponseNotificacion> getOferta(@Path("id") Integer id);

    @DELETE("oferta/{id}")
    Call<ResponseBody> deleteOferta(@Path("id") Integer id);

    @PUT("oferta/{id}")
    Call<ResponseBody> updateOferta(@Path("id") Integer id, @Body UpdateOfertaVM request);

   @POST("oferta/")
    Call<ResponseBody> createOferta (@Body CreateOfertaRequest request);

   @POST("oferta/filtrar/")
    Call<List<OfertasResponse>> getAllOfertasRecibidas(@Body FiltrarOfertaRequest request);

    @GET("oferta/estado/{id}")
    Call<FinalizarTrueque> getfinalizartrueque(@Path("id") Integer id);

    @PUT("oferta/estado/{id}")
    Call<ResponseBody> updatefinalizartrueque(@Path("id") Integer id, @Body UpdateFinalizarVM request);

    @PUT("oferta/comentario/{id}")
    Call<ResponseBody> updatecomentariotrueque(@Path("id") Integer id, @Body UpdateComentarVM request);

}
