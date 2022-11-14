package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class QueOfrecesACambio extends AppCompatActivity {

    private FloatingActionButton btn;
    private RecyclerView _recyclerView;
    IPublicacionService service;
    private AdapterOfrecerACambio _adapter;
    private List<Publicacion> lista = new ArrayList<>();
    private Integer idUsuario;
    private Integer idPublicacion;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ofreces_acambio);


        idUsuario = getIntent().getIntExtra("idUsuario",0);
        idPublicacion = getIntent().getIntExtra("idPublicacion",0);
        _recyclerView =(RecyclerView) findViewById(R.id.rvPublicacionesOfrecer);
        service= Apis.getPublicacionService();
        Call<List<Publicacion>> call =service.getPublicaciones(idUsuario);

        context = this;

        call.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, retrofit2.Response<List<Publicacion>> response) {
                if(response.isSuccessful()) {
                    try{
                        lista = response.body();
                        _adapter = new AdapterOfrecerACambio(context, lista,idPublicacion);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                        _recyclerView.setLayoutManager(gridLayoutManager);
                        _recyclerView.setHasFixedSize(true);
                        _recyclerView.setAdapter(_adapter);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() { }.getType();

                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(context, item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getApplicationContext(),"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });
    }

}