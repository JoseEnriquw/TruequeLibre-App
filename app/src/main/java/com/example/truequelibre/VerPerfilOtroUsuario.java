package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.truequelibre.Entity.CalificacionUsuario;
import com.example.truequelibre.Entity.Estado;
import com.example.truequelibre.Entity.Persona;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IUsuarioService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerPerfilOtroUsuario extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private AdapterComentariosMiPerfil _adapter;

    ImageView imageVOtroPerfil;
    TextView  tvNombreApellidoComentarioOtroPerfil;
    RatingBar ratingBarComentarioOtroPerfil;
    private IUsuarioService service;
    private Usuario usuario;
    private Integer idUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil_otro_usuario);

        _recyclerView =(RecyclerView) findViewById(R.id.rvComentariosMiPerfilOther);

        List<CalificacionUsuario> lista = new ArrayList<CalificacionUsuario>();
        RatingBar ratingBar=new RatingBar(this);
        Date date = new Date(2022/11/05);


        _adapter= new AdapterComentariosMiPerfil(this,lista);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);
        imageVOtroPerfil= findViewById(R.id.ivFotoPerfilOther);
        tvNombreApellidoComentarioOtroPerfil=findViewById(R.id.tvNombreApellidoPerfilOther);
        ratingBarComentarioOtroPerfil = findViewById(R.id.rbMiPerfilOther);

        tvNombreApellidoComentarioOtroPerfil.setText(getIntent().getStringExtra("nombreApellidoUsuario"));

        idUsuario = getIntent().getIntExtra("idUsuario", 0);
        service = Apis.getUsuarioService();
        Call<Usuario> callUsuario = service.getById(idUsuario);

        callUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    usuario = response.body();
                    cargarDatos(usuario);

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });


    }

    public void cargarDatos(Usuario usuario){
        tvNombreApellidoComentarioOtroPerfil.setText(usuario.getNombreApellido());
        Bitmap bitmap = ImagenConverter.convertByteToBitmap(usuario.getImagen());
        imageVOtroPerfil.setImageBitmap(bitmap);

    }

    public boolean validarCampos(){
        boolean bnd = true;
        LinearLayout llyParent = findViewById(R.id.linearLayoutLogin);
        int count = llyParent.getChildCount();
        for (int i=0;i<count;i++){
            if (llyParent.getChildAt(i) instanceof TextInputLayout){
                TextInputLayout layout = (TextInputLayout) llyParent.getChildAt(i);
                FrameLayout frameLayout = (FrameLayout) layout.getChildAt(0);
                int id = frameLayout.getChildAt(0).getId();
                EditText et = ((EditText) findViewById(id));
                if (et.length() == 0){
                    layout.setError("Complete este campo!");
                    bnd = false;
                }
            }
        }

        return bnd;
    }
}