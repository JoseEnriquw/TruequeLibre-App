package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.truequelibre.Entity.GetAllByCategoriaRequest;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Notify;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PublicacionesPorCategoria extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView _recyclerView;
    private AdapterArticulos _adapter;
    private Integer idUsuario;
    private Integer idCategoria;
    private SearchView search;
    IPublicacionService service;
    List<Publicacion> lista=new ArrayList<>();
    Context context;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones_por_categoria);

        search=(SearchView)findViewById(R.id.svFlitrarArticulos);
        context=this;
        idUsuario=(Integer)getIntent().getSerializableExtra("idUsuario");
        idCategoria=(Integer)getIntent().getSerializableExtra("IdCategoria");
        service= Apis.getPublicacionService();

        progressBar = (ProgressBar) findViewById(R.id.pbPublicacionesPorCategoria);
        await(false);

        GetAllByCategoriaRequest request= new GetAllByCategoriaRequest(idCategoria,idUsuario);
        Call<List<Publicacion>> call =service.getPublicacionesByCategoria(request);

        call.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, retrofit2.Response<List<Publicacion>> response) {
                await(true);
                if(response.isSuccessful()) {
                    lista = response.body();

                    _adapter= new AdapterArticulos(context,lista,idUsuario);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context
                            ,1,GridLayoutManager.VERTICAL,false);
                    _recyclerView.setLayoutManager(gridLayoutManager);
                    _recyclerView.setHasFixedSize(true);
                    _recyclerView.setAdapter(_adapter);
                }
                else
                {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Notify>>() {}.getType();
                    List<Notify> message = gson.fromJson(response.errorBody().charStream(),type);

                    for (Notify item: message) {
                        Toast.makeText(context,item.getMessage(),Toast.LENGTH_LONG);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG);
            }
        });

        _recyclerView =(RecyclerView) findViewById(R.id.rvarticulos);
        search.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        _adapter.filtrar(query,idUsuario,idCategoria);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.isEmpty())
        {
            _adapter.filtrar(newText,idUsuario,idCategoria);
        }
        return false;
    }

    private void await(boolean enabled){
        progressBar.setVisibility(enabled? View.GONE: View.VISIBLE);
        search.setEnabled(enabled);
    }
}