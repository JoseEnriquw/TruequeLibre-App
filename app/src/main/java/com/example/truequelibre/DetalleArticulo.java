package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.ArrayList;


public class DetalleArticulo extends AppCompatActivity {

    private ImageSlider imageSlider;
    private ImageView imgperfil;
    private TextView txtnombreyapellido;
    private TextView txttitulo;
    private TextView txtcondicion;
    private TextView txtdescripcion;
    private TextView txtintereses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulo);

        imageSlider = findViewById(R.id.imageSlider);
        imgperfil = findViewById(R.id.detallefotoperil);
        txtnombreyapellido = findViewById(R.id.detallenombreapellido);
        txttitulo = findViewById(R.id.detalletitulo);
        txtcondicion = findViewById(R.id.detallecondicion);
        txtdescripcion = findViewById(R.id.detalledescripcion);
        txtintereses = findViewById(R.id.detalleintereses);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/500/442/354/outrun-vaporwave-hd-wallpaper-preview.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/108/140/869/digital-digital-art-artwork-fantasy-art-drawing-hd-wallpaper-preview.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/946/379/721/artwork-landscape-mountains-forest-wallpaper-preview.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/846/616/937/digital-digital-art-artwork-illustration-drawing-hd-wallpaper-preview.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c4.wallpaperflare.com/wallpaper/816/451/655/sphere-art-artwork-1980s-wallpaper-preview.jpg", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


    }
}