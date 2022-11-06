package com.example.truequelibre.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static IPublicacionService apiService;

    public RetrofitClient(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(IPublicacionService.class);
    }

    public IPublicacionService getServies(){
        return apiService;
    }
}
