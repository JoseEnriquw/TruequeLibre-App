package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PublicacionesPorCategoria extends AppCompatActivity {
    private RecyclerView _recyclerView;
    private AdapterArticulos _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones_por_categoria);

        _recyclerView =(RecyclerView) findViewById(R.id.rvarticulos);


        List<EPublicaciones> lista = new ArrayList<EPublicaciones>();

        EPersona per = new EPersona("34695008d","regina","laurentino");
        EUsuario usu = new EUsuario(01,per,"regina@laurentino", "zarasa", true,"https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg");

        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));
        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));
        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));
        lista.add(new EPublicaciones("Bici", "Alta bici", "https://st.depositphotos.com/1063437/2491/i/450/depositphotos_24912571-stock-photo-bicycle-road-sign-and-bike.jpg", 01,"descripcion loca", usu));
        _adapter= new AdapterArticulos(this,lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);
    }
}