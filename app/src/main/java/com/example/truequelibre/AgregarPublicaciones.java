package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.truequelibre.Entity.Categoria;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.ICategoriaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AgregarPublicaciones extends AppCompatActivity {
    List<Categoria> lista= new ArrayList<>();
    ICategoriaService service;
    ArrayAdapter<String> adapter;
    AutoCompleteTextView dropDawnCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicaciones);

        service= Apis.getCategoriaService();
        Call<List<Categoria>> call =service.getCategorias();

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, retrofit2.Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    List<String> datos=new ArrayList<>();
                    for(Categoria item: lista) {
                        datos.add(item.getDescripcion());
                    }
                    adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, datos);
                    dropDawnCate =(AutoCompleteTextView) findViewById(R.id.ddCategoriaAgregarPublicacion);
                    dropDawnCate.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                System.out.println(lista);
            }
        });

    }
}