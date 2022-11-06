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


        List<Publicacion> lista = new ArrayList<Publicacion>();

        Persona per = new Persona("34695008d","regina","laurentino");
        Estado Estado = new Estado();
        Usuario usu = new Usuario(01,"mail","regina@laurentino", Estado, per);
        Categoria cat = new Categoria();
        Condicion CONDI = new Condicion();

        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,"https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png",CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,"https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png",CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,"https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png",CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,"https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png",CONDI));
        lista.add(new Publicacion(1,usu,"teclado","para escribir",cat,cat,"https://ar-media.hptiendaenlinea.com/magefan_blog/C_mo_encender-apagar_la_iluminacion_del_teclado_1.png",CONDI));
        _adapter= new AdapterArticulos(this,lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);
    }
}