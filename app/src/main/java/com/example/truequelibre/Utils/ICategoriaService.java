package com.example.truequelibre.Utils;

import com.example.truequelibre.Entity.Categoria;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ICategoriaService {

    @GET("categoria/")
    Call<List<Categoria>> getCategorias();
}
