package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.truequelibre.Entity.PublicacionResponse;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.IPublicacionService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleArticulo extends AppCompatActivity {

    private ImageSlider imageSlider;
    private ImageView imgperfil;
    private TextView txtnombreyapellido;
    private TextView txttitulo;
    private TextView txtcondicion;
    private TextView txtdescripcion;
    private TextView txtintereses;
    private Button btnofertar;
    private Button btnoverperfil;
    IPublicacionService service;
    private ViewFlipper imageFlipper;
    private Integer idPublicacion;
    private Integer idUsuario;
    private PublicacionResponse publicacion;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulo);

        //imageSlider = findViewById(R.id.imageSlider);
        imgperfil = findViewById(R.id.detallefotoperil);
        txtnombreyapellido = findViewById(R.id.detallenombreapellido);
        txttitulo = findViewById(R.id.detalletitulo);
        txtcondicion = findViewById(R.id.detallecondicion);
        txtdescripcion = findViewById(R.id.detalledescripcion);
        txtintereses = findViewById(R.id.detalleintereses);
        btnofertar =  findViewById(R.id.btnaceptar);
        btnoverperfil =  findViewById(R.id.btndetalleverperfil);

        context=this;

        imageFlipper = (ViewFlipper)findViewById( R.id.image_flipper );
        idPublicacion = getIntent().getIntExtra("idPublicacion",0);
        idUsuario = getIntent().getIntExtra("idUsuario",0);

        btnofertar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext().getApplicationContext(),QueOfrecesACambio.class);
                intent.putExtra("idUsuario", idUsuario);
                intent.putExtra("idPublicacion",idPublicacion);
                view.getContext().startActivity(intent);
            }
        }
        );

        btnoverperfil.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent= new Intent(view.getContext().getApplicationContext(),VerPerfilOtroUsuario.class);
                                               intent.putExtra("idUsuario", publicacion.getUsuario().getIdUsuario());
                                               intent.putExtra("nombreApellidoUsuario", publicacion.getUsuario().getNombreApellido());
                                               view.getContext().startActivity(intent);
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
        txttitulo.setText(publicacion.getNombre());
        txtdescripcion.setText(publicacion.getDescripcion());
        txtintereses.setText(publicacion.getInteres());
        txtcondicion.setText(publicacion.getCondicion());
        txtnombreyapellido.setText(publicacion.getUsuario().getNombreApellido());
        if (publicacion.getUsuario().getFotoPerfil() != null){
            Bitmap bitmapUsuario =  ImagenConverter.convertByteToBitmap(publicacion.getUsuario().getFotoPerfil());
            imgperfil.setImageBitmap(bitmapUsuario);
        }
    }


}