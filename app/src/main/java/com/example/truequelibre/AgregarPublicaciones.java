package com.example.truequelibre;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.ResponseBody;
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
    private Integer idUsuario;
    FloatingActionButton photobutton;
    AlertDialog.Builder builder;
    ImageView imageViewarray[]=new ImageView[5];
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    int banderin;
    Context context;

    private ExtendedFloatingActionButton btnAgregar;
    private TextInputEditText txtTitulo;
    private TextInputEditText txtDescripcion;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicaciones);
        context=this;

        idUsuario = getIntent().getIntExtra("idUsuario",0);
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

        dropDawnCate =(AutoCompleteTextView) findViewById(R.id.ddCategoriaAgregarPublicacion);
        dropDawnCatePretendida =(AutoCompleteTextView) findViewById(R.id.ddCategoriaPretendidaAgregarPublicacion);
        dropDownCondiciones =(AutoCompleteTextView) findViewById(R.id.ddCondicionAgregarPublicacion);
        dropDownLocalidades =(AutoCompleteTextView) findViewById(R.id.ddUbicacionAgregarPublicacion);
        dropDownLocalidadesPretendida =(AutoCompleteTextView) findViewById(R.id.ddUbicacionPretendidaAgregarPublicacion);

        btnAgregar = (ExtendedFloatingActionButton) findViewById(R.id.btnPublicarAgregarPublicacion);
        txtTitulo =(TextInputEditText) findViewById(R.id.txtTituloAgregar);
        txtDescripcion = (TextInputEditText)findViewById(R.id.txtDescripcionAgregar);
        progressBar =(ProgressBar) findViewById(R.id.pbAgregarPublicacion);

        await(true);

        service= Apis.getPublicacionService();
        Call<PublicacionDropdown> call =service.getPublicacionDropdown();

        call.enqueue(new Callback<PublicacionDropdown>() {
            @Override
            public void onResponse(Call<PublicacionDropdown> call, retrofit2.Response<PublicacionDropdown> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    adapterCategorias = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getCategorias());
                    dropDawnCate.setAdapter(adapterCategorias);
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
                    dropDownCondiciones.setAdapter(adapterCondiciones);

                    dropDownCondiciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            condicion = adapterCondiciones.getItem(position);
                        }
                    });

                    adapterLocalidad = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getLocalidades());
                    dropDownLocalidades.setAdapter(adapterLocalidad);
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
                }else
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
            public void onFailure(Call<PublicacionDropdown> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(context,"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });

        photobutton.setOnClickListener(view -> mGetContet.launch("image/*"));

    }

    public void Publicar(View v){
        builder.setMessage("Una vez agregada, debe esperar a que esta sea aprobada")
                .setCancelable(false)
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (validarCampos()){
                            await(false);
                            String nombre = ((TextInputEditText)findViewById(R.id.txtTituloAgregar)).getText().toString();
                            String descripcion = ((TextInputEditText)findViewById(R.id.txtDescripcionAgregar)).getText().toString();
                            byte[] imageInByte = convertImgViewToArray();

                            PublicacionCreateRequest publicacion = new PublicacionCreateRequest(idUsuario,nombre,descripcion,
                                    categoria.getIdCategoria(),categoriaPretendida.getIdCategoria(),
                                    condicion.getIdCondicion(), ubicacion.getIdLocalidad(),ubicacionPretendida.getIdLocalidad(), imageInByte);
                            postPublicacionCreate(publicacion);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error: complete todos los campos!", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(context,"Cancelado",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Desea agregar esta publicacion?");
        alert.show();


    }

    public void await(boolean enabled){
        progressBar.setVisibility(enabled ? View.GONE : View.VISIBLE);
        btnAgregar.setEnabled(enabled);
        dropDawnCate.setEnabled(enabled);
        dropDawnCatePretendida.setEnabled(enabled);
        dropDownLocalidades.setEnabled(enabled);
        dropDownLocalidadesPretendida.setEnabled(enabled);
        dropDownCondiciones.setEnabled(enabled);
        txtTitulo.setEnabled(enabled);
        txtDescripcion.setEnabled(enabled);
    }

    public void limpiarCampos(){
        dropDawnCate.setText("");
        dropDawnCatePretendida.setText("");
        dropDownLocalidades.setText("");
        dropDownLocalidadesPretendida.setText("");
        dropDownCondiciones.setText("");
        txtTitulo.setText("");
        txtDescripcion.setText("");
    }

    public byte[] convertImgViewToArray(){
        try{
            if (img1.getDrawable() != null){
            Bitmap bitmap = ((BitmapDrawable) img1.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return baos.toByteArray();
            }
            else{
                return new byte[0];
            }
        }
        catch (Exception ex){
            return new byte[0];
        }
    }

    public boolean validarCampos(){
        String nombre = ((TextInputEditText)findViewById(R.id.txtTituloAgregar)).getText().toString();
        String descripcion = ((TextInputEditText)findViewById(R.id.txtDescripcionAgregar)).getText().toString();
        if (img1.getDrawable() == null || nombre.equals("") || descripcion.equals("") || categoria == null ||
                categoriaPretendida == null || condicion == null || ubicacion == null || ubicacionPretendida==null)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean postPublicacionCreate(PublicacionCreateRequest publicacion){
        service= Apis.getPublicacionService();
        Call<Void> call = service.create(publicacion);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                await(true);
                if(response.isSuccessful()){
                    if(response.code() == HttpURLConnection.HTTP_CREATED){

                        Toast.makeText(AgregarPublicaciones.this,"Publicación agregada con exito!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.putExtra("idUsuario", idUsuario);
                        context.startActivity(intent);
                    }
                    else{
                        Toast.makeText(AgregarPublicaciones.this,"Error al agregar publicación!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {}.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                    for (Error item: message) {
                        Toast.makeText(context,item.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AgregarPublicaciones.this,"Error al agregar publicación!", Toast.LENGTH_LONG).show();
                await(true);
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
        AlertDialog alert = builder.create();
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