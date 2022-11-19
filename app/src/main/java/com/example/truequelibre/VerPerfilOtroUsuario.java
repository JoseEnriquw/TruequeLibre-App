package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequelibre.Entity.CalificacionUsuario;
import com.example.truequelibre.Entity.Estado;
import com.example.truequelibre.Entity.Persona;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.ICalificacionUsuariosService;
import com.example.truequelibre.Utils.IUsuarioService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.example.truequelibre.Utils.Notify;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerPerfilOtroUsuario extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private AdapterComentariosMiPerfil _adapter;
    ICalificacionUsuariosService serviceuser;
    ImageView imageVOtroPerfil;
    TextView  tvNombreApellidoComentarioOtroPerfil;
    RatingBar ratingBarComentarioOtroPerfil;
    private IUsuarioService service;
    private Usuario usuario;
    private Integer idUsuario;
    List<CalificacionUsuario> lista ;
    RatingBar ratingBar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil_otro_usuario);

        _recyclerView =(RecyclerView) findViewById(R.id.rvComentariosMiPerfilOther);

        context= this;

        imageVOtroPerfil= findViewById(R.id.ivFotoPerfilOther);
        tvNombreApellidoComentarioOtroPerfil=findViewById(R.id.tvNombreApellidoPerfilOther);
        ratingBarComentarioOtroPerfil = findViewById(R.id.rbMiPerfilOther);
        ratingBarComentarioOtroPerfil.setIsIndicator(true);

        tvNombreApellidoComentarioOtroPerfil.setText(getIntent().getStringExtra("nombreApellidoUsuario"));



        idUsuario = getIntent().getIntExtra("idUsuario", 0);

        cargarcomentarios();
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

    public void cargarcomentarios(){
        serviceuser= Apis.getCalificacionUsuariosService();
        Call<List<CalificacionUsuario>> call =serviceuser.getCalificacionesByUsuario(idUsuario);

        call.enqueue(new Callback<List<CalificacionUsuario>>() {
            @Override
            public void onResponse(Call<List<CalificacionUsuario>> call, retrofit2.Response<List<CalificacionUsuario>> response) {
                if(response.isSuccessful()) {
                    lista = response.body();

                    _adapter= new AdapterComentariosMiPerfil(context,lista);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
                    _recyclerView.setLayoutManager(gridLayoutManager);
                    _recyclerView.setHasFixedSize(true);
                    _recyclerView.setAdapter(_adapter);

                    float promedio=0;
                    for (CalificacionUsuario item: lista)
                    {
                        promedio+=item.getEstrellas();
                    }
                    promedio/=lista.size();

                    ratingBarComentarioOtroPerfil.setRating(promedio);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Notify>>() { }.getType();

                    List<Notify> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Notify item : message) {
                        Toast.makeText(context, item.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CalificacionUsuario>> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });
    }
}
