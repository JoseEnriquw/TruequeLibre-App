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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.truequelibre.Entity.Categoria;
import com.example.truequelibre.Entity.Condicion;
import com.example.truequelibre.Entity.Dropdown.CategoriaDropdown;
import com.example.truequelibre.Entity.Dropdown.CondicionDropdown;
import com.example.truequelibre.Entity.Dropdown.LocalidadDropdown;
import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.Localidad;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.PublicacionCreate;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.ICategoriaService;
import com.example.truequelibre.Utils.PhotoUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class AgregarPublicaciones extends AppCompatActivity {
    PublicacionDropdown lista= new PublicacionDropdown();
    IPublicacionService service;
    ArrayAdapter<CategoriaDropdown> adapterCategorias;
    AutoCompleteTextView dropDawnCate;
    AutoCompleteTextView dropDawnCatePretendida;
    ArrayAdapter<CondicionDropdown> adapterCondiciones;
    AutoCompleteTextView dropDownCondiciones;
    ArrayAdapter<LocalidadDropdown> adapterLocalidad;
    AutoCompleteTextView dropDownLocalidades;
    AutoCompleteTextView dropDownLocalidadesPretendida;

    private CategoriaDropdown categoria;
    private CategoriaDropdown categoriaPretendida;
    private LocalidadDropdown ubicacion;
    private LocalidadDropdown ubicacionPretendida;
    private CondicionDropdown condicion;

    FloatingActionButton photobutton;
    AlertDialog.Builder builder;
    ImageView imageViewarray[]=new ImageView[5];
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    int banderin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicaciones);
        photobutton = findViewById(R.id.btnAgregarImagenesPublicacion);
        builder = new AlertDialog.Builder(this);
        img1 = findViewById(R.id.imgvagregarpublicaciones1);
        img2 = findViewById(R.id.imgvagregarpublicaciones2);
        img3 = findViewById(R.id.imgvagregarpublicaciones3);
        img4 = findViewById(R.id.imgvagregarpublicaciones4);
        img5 = findViewById(R.id.imgvagregarpublicaciones5);

        service= Apis.getPublicacionService();
        Call<PublicacionDropdown> call =service.getPublicacionDropdown();
        imageViewarray[0] = img1;
        imageViewarray[1] = img2;
        imageViewarray[2] = img3;
        imageViewarray[3] = img4;
        imageViewarray[4] = img5;

        service= Apis.getCategoriaService();
        Call<List<Categoria>> call =service.getCategorias();

        call.enqueue(new Callback<PublicacionDropdown>() {
            @Override
            public void onResponse(Call<PublicacionDropdown> call, retrofit2.Response<PublicacionDropdown> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    adapterCategorias = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getCategorias());
                    dropDawnCate =(AutoCompleteTextView) findViewById(R.id.ddCategoriaAgregarPublicacion);
                    dropDawnCate.setAdapter(adapterCategorias);
                    dropDawnCatePretendida =(AutoCompleteTextView) findViewById(R.id.ddCategoriaPretendidaAgregarPublicacion);
                    dropDawnCatePretendida.setAdapter(adapterCategorias);

                    dropDawnCate.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            categoria = adapterCategorias.getItem(position);
                        }
                    });
                    dropDawnCatePretendida.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            categoriaPretendida = adapterCategorias.getItem(position);
                        }
                    });

                    adapterCondiciones = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getCondiciones());
                    dropDownCondiciones =(AutoCompleteTextView) findViewById(R.id.ddCondicionAgregarPublicacion);
                    dropDownCondiciones.setAdapter(adapterCondiciones);

                    dropDownCondiciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            condicion = adapterCondiciones.getItem(position);
                        }
                    });

                    adapterLocalidad = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getLocalidades());
                    dropDownLocalidades =(AutoCompleteTextView) findViewById(R.id.ddUbicacionAgregarPublicacion);
                    dropDownLocalidades.setAdapter(adapterLocalidad);
                    dropDownLocalidadesPretendida =(AutoCompleteTextView) findViewById(R.id.ddUbicacionPretendidaAgregarPublicacion);
                    dropDownLocalidadesPretendida.setAdapter(adapterLocalidad);

                    dropDownLocalidades.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ubicacion = adapterLocalidad.getItem(position);
                        }
                    });

                    dropDownLocalidadesPretendida.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ubicacionPretendida = adapterLocalidad.getItem(position);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<PublicacionDropdown> call, Throwable t) {
                System.out.println(lista);
            }
        });

    }

    public void Publicar(View v){
        PublicacionCreate publicacion = new PublicacionCreate();
        // TITULO Y DESCRIPCION
        publicacion.setNombre(((TextInputEditText)findViewById(R.id.txtTituloAgregar)).getText().toString());
        publicacion.setDescripcion(((TextInputEditText)findViewById(R.id.txtDescripcionAgregar)).getText().toString());
        //CONDICION
        Condicion cond = new Condicion(condicion.getIdCondicion());
        publicacion.setCondicion(cond);

        // UBICACION
        Localidad loc = new Localidad(ubicacion.getIdLocalidad());
        publicacion.setUbicacion(loc);
        loc = new Localidad(ubicacionPretendida.getIdLocalidad());
        publicacion.setUbicacionPretendida(loc);

        // CATEGORIA
        Categoria cat = new Categoria(categoria.getIdCategoria());
        publicacion.setCategoria(cat);
        cat.setId(categoriaPretendida.getIdCategoria());
        publicacion.setInteres(cat);

        publicacion.toString();


        photobutton.setOnClickListener(view -> mGetContet.launch("image/*"));

    }

    public void alertdialog(){
        builder.setMessage("Solo podes cargar 5 imagenes")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();

                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
       // alert.setTitle("AlertDialogExample");
        alert.show();
    }

    ActivityResultLauncher<String> mGetContet = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result !=null){
                        for ( int i= 0; i<5; i++ ) {
                            if(imageViewarray[i].getDrawable()== null) {
                                imageViewarray[i].setImageURI(result);

                                return;
                            }
                           i++;
                        }

                    }
                }
            }
    );
}