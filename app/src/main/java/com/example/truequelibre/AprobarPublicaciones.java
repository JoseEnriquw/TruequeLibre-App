package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.truequelibre.Entity.PublicacionResponse;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.IPublicacionService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AprobarPublicaciones extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvNombreApellido;
    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvCondicion;
    private TextView tvInteres;
    private Button btnAceptar;
    private Button btnRechazar;
    private IPublicacionService service;
    private ViewFlipper imageFlipper;
    private Integer idPublicacion;
    private Integer idUsuario;
    private PublicacionResponse publicacion;
    private Context context;
    private Button btnVerPerfil;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_aprobar_publicaciones);
        imageFlipper = (ViewFlipper)findViewById( R.id.image_flipperadmin);
        idPublicacion = getIntent().getIntExtra("idPublicacion",0);
        idUsuario = getIntent().getIntExtra("idUsuario",0);
        imageView= findViewById(R.id.detallefotoperiladmin);
        tvNombreApellido=findViewById(R.id.detallenombreapellidoadmin);
        tvNombre=findViewById(R.id.detalletituloadmin);
        tvDescripcion=findViewById(R.id.detallecondicionadmin);
        tvCondicion=findViewById(R.id.detallecondicionadmin);
        tvInteres=findViewById(R.id.tvLeinteresaarticuloadmin);
        btnVerPerfil = findViewById(R.id.btndetalleverperfiladmin);

        btnAceptar= findViewById(R.id.btnaceptaradmin);
        btnRechazar=  findViewById(R.id.btnrechazaradmin);

        service= Apis.getPublicacionService();

        btnAceptar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = service.updateAdmin(publicacion.getId(), new PublicacionEditarRequestAdmin(1));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Publicacion aprobada con exito!", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(context,pantallaAdmin.class);
                            context.startActivity(i);
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
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getCause() + " \n" + t.getMessage());
                        Toast.makeText(context, "Error al aprobar la publicación!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        btnRechazar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = service.updateAdmin(publicacion.getId(), new PublicacionEditarRequestAdmin(5));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(context,"Publicacion rechazada con exito!",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(context,pantallaAdmin.class);
                            context.startActivity(i);
                        }
                        else
                        {
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Error>>() {}.getType();
                            List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                            for (Error item: message) {
                                Toast.makeText(context,item.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getCause()+ " \n"+t.getMessage());
                        Toast.makeText(context,"Error al rechazar la publicación!", Toast.LENGTH_LONG).show();
                    }
                });

            }
                                          }
        );


        service= Apis.getPublicacionService();
        Call<PublicacionResponse> callPublicacion =service.getOne(idPublicacion);

        callPublicacion.enqueue(new Callback<PublicacionResponse>() {
            @Override
            public void onResponse(Call<PublicacionResponse> call, Response<PublicacionResponse> response) {
                if(response.isSuccessful()) {
                    publicacion = response.body();
                    cargarControles(publicacion);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {
                    }.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(context, item.getMessage(), Toast.LENGTH_LONG);
                    }
                }
            }
            @Override
            public void onFailure(Call<PublicacionResponse> call, Throwable t) {

            }
        });

        btnVerPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VerPerfilOtroUsuario.class);
                intent.putExtra("idUsuario", publicacion.getUsuario().getIdUsuario());
                context.startActivity(intent);
            }
        });
    }

    public void cargarControles(PublicacionResponse publicacion){
        if (publicacion.getImagenes() != null){
            Bitmap bitmap = ImagenConverter.convertByteToBitmap(publicacion.getImagenes());
            ImageView imagen = new ImageView(getApplicationContext());
            imagen.setImageBitmap(bitmap);
            imageFlipper.addView(imagen);
            imageFlipper.setFlipInterval( 3000 ); //5s intervals
            imageFlipper.startFlipping();
        }
        tvNombre.setText(publicacion.getNombre());
        tvDescripcion.setText(publicacion.getDescripcion());
        tvInteres.setText(publicacion.getInteres());
        tvCondicion.setText(publicacion.getCondicion());
        tvNombreApellido.setText(publicacion.getUsuario().getNombreApellido());
        if (publicacion.getUsuario().getFotoPerfil() != null){
            Bitmap bitmapUsuario =  ImagenConverter.convertByteToBitmap(publicacion.getUsuario().getFotoPerfil());
            imageView.setImageBitmap(bitmapUsuario);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_admin,menu);
        return  true;
    }

    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itemSalirAdmin ){
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}