package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.PublicacionResponse;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.example.truequelibre.Utils.ImagenConverter;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

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
    private Publicacion publicacion;

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
        btnofertar =  findViewById(R.id.btndetalleofertar);
        btnoverperfil =  findViewById(R.id.btndetalleverperfil);


        imageFlipper = (ViewFlipper)findViewById( R.id.image_flipper );

        /*ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/108/140/869/digital-digital-art-artwork-fantasy-art-drawing-hd-wallpaper-preview.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/946/379/721/artwork-landscape-mountains-forest-wallpaper-preview.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/846/616/937/digital-digital-art-artwork-illustration-drawing-hd-wallpaper-preview.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/816/451/655/sphere-art-artwork-1980s-wallpaper-preview.jpg", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);*/

        btnofertar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext().getApplicationContext(),QueOfrecesACambio.class);
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

        idPublicacion = getIntent().getIntExtra("idPublicacion",0);
        service= Apis.getPublicacionService();
        Call<Publicacion> callPublicacion =service.getOneDetail(idPublicacion);

        callPublicacion.enqueue(new Callback<Publicacion>() {
            @Override
            public void onResponse(Call<Publicacion> call, Response<Publicacion> response) {
                if(response.isSuccessful()) {
                    publicacion = response.body();
                    cargarControles(publicacion);
                }
            }
            @Override
            public void onFailure(Call<Publicacion> call, Throwable t) {

            }
        });

    }

    public void cargarControles(Publicacion publicacion){
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