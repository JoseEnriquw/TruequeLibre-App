package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.truequelibre.Entity.CreateCalificacionRequest;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.ICalificacionUsuariosService;
import com.example.truequelibre.Utils.IUsuarioService;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalificarUsuario extends AppCompatActivity {
    Usuario usuario;
    TextView tvNombreApellido;
    TextView comentario;
    Button boton;
    ImageView ivFoto;
    private ICalificacionUsuariosService service;
    private IUsuarioService usuarioService;
    RatingBar ratingBar;
    Context context;
    Integer idUsuarioCalificado;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar_usuario);

        context= this;

        tvNombreApellido= (TextView)findViewById(R.id.tvNombreApellidoPerfilcalificar);
        ivFoto=(ImageView)findViewById(R.id.ivFotoPerfilcalificar);
        ratingBar=(RatingBar)findViewById(R.id.rbMiPerfilcalificar);
        comentario= (TextView)findViewById(R.id.etComentariosMiPerfilcalificar);
        boton=(Button) findViewById(R.id.buttoncalificar);
        ratingBar.setIsIndicator(false);

        idUsuarioCalificado = getIntent().getIntExtra("idcalificado", 0);
        usuarioService = Apis.getUsuarioService();

        Call<Usuario> callUsuario = usuarioService.getById(idUsuarioCalificado);

        callUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    usuario = response.body();
                    tvNombreApellido.setText(usuario.getNombreApellido());
                    if (usuario.getImagen() != null){
                        byte[] byteArray =  Base64.decode(usuario.getImagen(), Base64.DEFAULT);
                        ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
                        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                        ivFoto.setImageBitmap(theImage);
                    }
                } else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {
                    }.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(context, item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();

            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos())
                {
                    calicarficarUsuario();
                }
            }
        });


    }

    public void calicarficarUsuario(){
        CreateCalificacionRequest calificacion = new CreateCalificacionRequest();

        calificacion.setEstrellas((short) ratingBar.getRating());
        calificacion.setComentario((String) comentario.getText());
        Call<ResponseBody> call =service.createCalificacion(calificacion);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context,"usuario calificado con exito", Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean validarCampos(){
        boolean bnd = true;
        RelativeLayout llyParent = findViewById(R.id.relativeLayoutCalificar);
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