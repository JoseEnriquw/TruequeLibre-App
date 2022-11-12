package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class pantallaAdmin extends AppCompatActivity {
    private RecyclerView _recyclerView;
    private AdapterPublicacionesAdmin _adapter;
    IPublicacionService service;
    private List<Publicacion> lista = new ArrayList<>();
    Context contex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_admin);

        _recyclerView =(RecyclerView) findViewById(R.id.rvarticulosadmin);
        contex = getApplicationContext();
        service= Apis.getPublicacionService();
        Call<List<Publicacion>> call =service.GetAllAdmin();

        call.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, retrofit2.Response<List<Publicacion>> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    _adapter = new AdapterPublicacionesAdmin(contex, lista);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(contex, 1, GridLayoutManager.VERTICAL, false);
                    _recyclerView.setLayoutManager(gridLayoutManager);
                    _recyclerView.setHasFixedSize(true);
                    _recyclerView.setAdapter(_adapter);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() { }.getType();

                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(contex, item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(contex,"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });
    }
}