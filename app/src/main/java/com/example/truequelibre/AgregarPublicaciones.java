package com.example.truequelibre;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.truequelibre.Entity.Dropdown.CategoriaDropdown;
import com.example.truequelibre.Entity.Dropdown.CondicionDropdown;
import com.example.truequelibre.Entity.Dropdown.LocalidadDropdown;
import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.Publicacion;
import com.example.truequelibre.Entity.PublicacionCreateRequest;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        imageViewarray[0] = img1;
        imageViewarray[1] = img2;
        imageViewarray[2] = img3;
        imageViewarray[3] = img4;
        imageViewarray[4] = img5;

        service= Apis.getPublicacionService();
        Call<PublicacionDropdown> call =service.getPublicacionDropdown();

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

        photobutton.setOnClickListener(view -> mGetContet.launch("image/*"));

    }

    public void Publicar(View v){
        String nombre = ((TextInputEditText)findViewById(R.id.txtTituloAgregar)).getText().toString();
        String descripcion = ((TextInputEditText)findViewById(R.id.txtDescripcionAgregar)).getText().toString();
        byte[] imageInByte = convertImgViewToArray();
        PublicacionCreateRequest publicacion = new PublicacionCreateRequest(1,nombre,descripcion,
                categoria.getIdCategoria(),categoriaPretendida.getIdCategoria(),
                condicion.getIdCondicion(), ubicacion.getIdLocalidad(),ubicacionPretendida.getIdLocalidad(), imageInByte);
        postPublicacionCreate(publicacion);
    }

    public byte[] convertImgViewToArray(){
        Bitmap bitmap = ((BitmapDrawable) img1.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public boolean postPublicacionCreate(PublicacionCreateRequest publicacion){
        service= Apis.getPublicacionService();
        Call<Void> call = service.create(publicacion);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    if(response.code() == HttpURLConnection.HTTP_CREATED){
                        Toast.makeText(AgregarPublicaciones.this,"Publicación agregada con exito!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(AgregarPublicaciones.this,"Error al agregar publicación!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(AgregarPublicaciones.this,"Error al agregar publicación!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AgregarPublicaciones.this,"Error al agregar publicación!", Toast.LENGTH_LONG).show();
            }
        });
        return true;
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