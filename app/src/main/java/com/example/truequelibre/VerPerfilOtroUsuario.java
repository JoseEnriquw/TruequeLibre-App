package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VerPerfilOtroUsuario extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private AdapterComentariosMiPerfil _adapter;

    ImageView imageVOtroPerfil;
    TextView  tvNombreApellidoComentarioOtroPerfil;
    RatingBar ratingBarComentarioOtroPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil_otro_usuario);

        _recyclerView =(RecyclerView) findViewById(R.id.rvComentariosMiPerfilOther);

        List<CalificacionUsuario> lista = new ArrayList<CalificacionUsuario>();
        RatingBar ratingBar=new RatingBar(this);
        Date date = new Date(2022/11/05);

        Persona per = new Persona("34695008d","regina","laurentino");
        Estado Estado = new Estado();
        Usuario usu = new Usuario(01,"mail","regina@laurentino", Estado, per);

        lista.add(new CalificacionUsuario(1,usu,ratingBar,"Bueno!!",date));
        lista.add(new CalificacionUsuario(2,usu,ratingBar,"Muy Bueno!!",date));
        lista.add(new CalificacionUsuario(3,usu,ratingBar,"Excelente!!",date));

        _adapter= new AdapterComentariosMiPerfil(this,lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        imageVOtroPerfil= findViewById(R.id.ivFotoPerfilOther);
        tvNombreApellidoComentarioOtroPerfil=findViewById(R.id.tvNombreApellidoPerfilOther);
        ratingBarComentarioOtroPerfil = findViewById(R.id.rbMiPerfilOther);



    }
}