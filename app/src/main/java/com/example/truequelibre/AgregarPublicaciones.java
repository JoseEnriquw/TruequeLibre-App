package com.example.truequelibre;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.truequelibre.Entity.Categoria;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.ICategoriaService;
import com.example.truequelibre.Utils.PhotoUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AgregarPublicaciones extends AppCompatActivity {
    List<Categoria> lista= new ArrayList<>();
    ICategoriaService service;
    ArrayAdapter<String> adapter;
    AutoCompleteTextView dropDawnCate;
    FloatingActionButton photobutton;
    AlertDialog.Builder builder;
    private PhotoUtils photoUtils;
    private AlertDialog _photoDialog;
    private Uri mImageUri;
    private static final int ACTIVITY_SELECT_IMAGE = 1020,
            ACTIVITY_SELECT_FROM_CAMERA = 1040, ACTIVITY_SHARE = 1030;

    //prueba
    private static final String TAG = "MainActivity";
    private static final int PICTURE_RESULT = 122 ;
    private ContentValues values;
    private Uri imageUri;
    private ImageView myImageView;
    private Bitmap thumbnail;

    String imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicaciones);
        photobutton = findViewById(R.id.btnAgregarImagenesPublicacion);
        myImageView = findViewById(R.id.imgvagregarpublicaciones);
        builder = new AlertDialog.Builder(this);
        service= Apis.getCategoriaService();
        Call<List<Categoria>> call =service.getCategorias();

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, retrofit2.Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    List<String> datos=new ArrayList<>();
                    for(Categoria item: lista) {
                        datos.add(item.getDescripcion());
                    }
                    adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, datos);
                    dropDawnCate =(AutoCompleteTextView) findViewById(R.id.ddCategoriaAgregarPublicacion);
                    dropDawnCate.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                System.out.println(lista);
            }
        });


        photobutton.setOnClickListener(view -> mGetContet.launch("image/*"));

    }
    ActivityResultLauncher<String> mGetContet = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result !=null){
                        myImageView.setImageURI(result);
                    }
                }
            }
    );
}