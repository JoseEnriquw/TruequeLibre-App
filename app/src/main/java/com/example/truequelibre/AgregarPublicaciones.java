package com.example.truequelibre;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class AgregarPublicaciones extends AppCompatActivity {

    private FloatingActionButton btnAgregarImagenes;
    private final int GALLERY_REQ_CODE = 1000;
    private RecyclerView recyclerView;
    private View layoutDialogAgregarImagenes;
    private AdapterAgregarImagenPublicacion adapter;
    private ArrayList<Uri> uri = new ArrayList<>();

    private static final int Read_Permission = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicaciones);

        btnAgregarImagenes = findViewById(R.id.btnAgregarImagenesPublicacion);
        LayoutInflater inflater = LayoutInflater.from(this);
        layoutDialogAgregarImagenes = inflater.inflate(R.layout.dialog_agregar_imagenes_publicacion, null);
        recyclerView = (RecyclerView) layoutDialogAgregarImagenes.findViewById(R.id.rvAgregarImagenesPublicacion);

        adapter = new AdapterAgregarImagenPublicacion(uri,layoutDialogAgregarImagenes.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Read_Permission);
        }

        btnAgregarImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGaleria = new Intent();
                iGaleria.setType("image/*");
                iGaleria.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                iGaleria.setAction(Intent.ACTION_GET_CONTENT);
                iGaleria.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(Intent.createChooser(iGaleria,"Seleccione una foto"),1);



            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 1 && resultCode == RESULT_OK){
            if(data.getClipData() != null){
                int x = data.getClipData().getItemCount();

                for(int i=0;i<x;i++){
                    uri.add(data.getClipData().getItemAt(i).getUri());
                }
                adapter.notifyItemRangeInserted(0,x);
            }
            else if (data.getData() != null){
                String imageURI = data.getData().getPath();
                uri.add(Uri.parse(imageURI));
                adapter.notifyItemInserted(uri.size()-1);

            }
        }

        recyclerView = (RecyclerView) layoutDialogAgregarImagenes.findViewById(R.id.rvAgregarImagenesPublicacion);
        adapter = new AdapterAgregarImagenPublicacion(uri,layoutDialogAgregarImagenes.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter);

        new MaterialAlertDialogBuilder(AgregarPublicaciones.this)
                .setTitle("TITULO")
                .setView(layoutDialogAgregarImagenes)
                .show();
    }
}