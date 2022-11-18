package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.UpdateOfertaVM;
import com.example.truequelibre.Entity.UpdatePersonaVM;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPersonaService {
    @PUT("/{id}")
    Call<ResponseBody> updatePersona(@Path("id") Integer id, @Body UpdatePersonaVM request);
}
