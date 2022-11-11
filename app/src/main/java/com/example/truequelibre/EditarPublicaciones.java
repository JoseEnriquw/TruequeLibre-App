package com.example.truequelibre;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.truequelibre.Entity.Dropdown.CategoriaDropdown;
import com.example.truequelibre.Entity.Dropdown.CondicionDropdown;
import com.example.truequelibre.Entity.Dropdown.LocalidadDropdown;
import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.PublicacionEditar;
import com.example.truequelibre.Entity.PublicacionEditarRequest;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.IPublicacionService;
import com.example.truequelibre.Utils.ImagenConverter;
import com.example.truequelibre.ui.publicaciones.PublicacionesFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPublicaciones extends AppCompatActivity {

    EditText txtTitulo;
    EditText txtDescripcion;
    IPublicacionService service;
    PublicacionEditar publicacion;
    PublicacionDropdown lista= new PublicacionDropdown();
    ArrayAdapter<CategoriaDropdown> adapterCategorias;
    AutoCompleteTextView dropDawnCate;
    AutoCompleteTextView dropDawnCatePretendida;
    ArrayAdapter<CondicionDropdown> adapterCondiciones;
    AutoCompleteTextView dropDownCondiciones;
    ArrayAdapter<LocalidadDropdown> adapterLocalidad;
    AutoCompleteTextView dropDownLocalidades;
    AutoCompleteTextView dropDownLocalidadesPretendida;
    ImageView img1;
    private Integer idPublicacion;

    private CategoriaDropdown categoria;
    private CategoriaDropdown categoriaPretendida;
    private LocalidadDropdown ubicacion;
    private LocalidadDropdown ubicacionPretendida;
    private CondicionDropdown condicion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicaciones);

        txtTitulo = (EditText) findViewById(R.id.txtTituloEditar);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcionEditar);
        img1 = findViewById(R.id.imgvEditarpublicaciones1);

        idPublicacion = getIntent().getIntExtra("idPublicacion",0);

        service= Apis.getPublicacionService();
        Call<PublicacionDropdown> call =service.getPublicacionDropdown();

        call.enqueue(new Callback<PublicacionDropdown>() {
            @Override
            public void onResponse(Call<PublicacionDropdown> call, retrofit2.Response<PublicacionDropdown> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    adapterCategorias = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getCategorias());
                    dropDawnCate =(AutoCompleteTextView) findViewById(R.id.ddCategoriaEditarPublicacion);
                    dropDawnCate.setAdapter(adapterCategorias);
                    dropDawnCatePretendida =(AutoCompleteTextView) findViewById(R.id.ddCategoriaPretendidaEditarPublicacion);
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
                    dropDownCondiciones =(AutoCompleteTextView) findViewById(R.id.ddCondicionEditarPublicacion);
                    dropDownCondiciones.setAdapter(adapterCondiciones);

                    dropDownCondiciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            condicion = adapterCondiciones.getItem(position);
                        }
                    });
                    adapterLocalidad = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getLocalidades());
                    dropDownLocalidades =(AutoCompleteTextView) findViewById(R.id.ddUbicacionEditarPublicacion);
                    dropDownLocalidades.setAdapter(adapterLocalidad);
                    dropDownLocalidadesPretendida =(AutoCompleteTextView) findViewById(R.id.ddUbicacionPretendidaEditarPublicacion);
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
                        Toast.makeText(getApplicationContext(),item.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<PublicacionDropdown> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getApplicationContext(),"Hubo un error al traer los datos de la base de datos :(",Toast.LENGTH_LONG).show();
            }
        });

        Integer idPublicacion = (Integer) getIntent().getSerializableExtra("idPublicacion");

        service= Apis.getPublicacionService();
        Call<PublicacionEditar> callPublicacion =service.getOne(idPublicacion);

        callPublicacion.enqueue(new Callback<PublicacionEditar>() {
            @Override
            public void onResponse(Call<PublicacionEditar> call, Response<PublicacionEditar> response) {
                if(response.isSuccessful()) {
                    publicacion = response.body();
                    cargarControles(publicacion);
                }
            }
            @Override
            public void onFailure(Call<PublicacionEditar> call, Throwable t) {

            }
        });

    }

    public void cargarControles(PublicacionEditar publicacion){
        if (publicacion.getImagenes() != null){
            byte[] byteArray =  Base64.decode(publicacion.getImagenes(), Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            img1.setImageBitmap(theImage);
        }
        txtTitulo.setText(publicacion.getNombre());
        txtDescripcion.setText(publicacion.getDescripcion());
        dropDawnCate.setText(publicacion.getCategoria(),false);
        dropDawnCatePretendida.setText(publicacion.getInteres(),false);
        dropDownCondiciones.setText(publicacion.getCondicion(),false);
        dropDownLocalidades.setText(publicacion.getUbicacion(),false);
        dropDownLocalidadesPretendida.setText(publicacion.getUbicacionPretendida(),false);
    }

    public void Editar(View view){
        try{

            byte[] imageInByte = ImagenConverter.convertImgViewToArray(img1);
            findItemAdapter(publicacion.getCategoria(),"categoria",false);
            findItemAdapter(publicacion.getInteres(),"categoria",true);
            findItemAdapter(publicacion.getCondicion(),"condicion",false);
            findItemAdapter(publicacion.getUbicacion(),"ubicacion",false);
            findItemAdapter(publicacion.getUbicacionPretendida(),"ubicacion",true);
            PublicacionEditarRequest request = new PublicacionEditarRequest(
                    String.valueOf(txtTitulo.getText()),
                    String.valueOf(txtDescripcion.getText()),
                    categoria.getIdCategoria(),
                    categoriaPretendida.getIdCategoria(),
                    condicion.getIdCondicion(),
                    1,
                    ubicacion.getIdLocalidad(),
                    ubicacionPretendida.getIdLocalidad(),
                    imageInByte
            );
            postEditarPublicacion(request);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + " - " + ex.getCause());
            ex.printStackTrace();
        }
    }

    public void findItemAdapter(String filtro,String adapter ,boolean pretendida){

        switch (adapter){
            case "categoria":
                for (int i = 0; i<adapterCategorias.getCount(); i++){
                    if (adapterCategorias.getItem(i).getDescripcionCategoria().equals(filtro)){
                        if (pretendida){
                            categoriaPretendida =adapterCategorias.getItem(i);
                        }
                        else{
                            categoria =adapterCategorias.getItem(i);
                        }
                    }
                }
                break;
            case "condicion":
                for (int i = 0; i<adapterCondiciones.getCount(); i++){
                    if (adapterCondiciones.getItem(i).getDescripcionCondicion().equals(filtro)){
                        condicion = adapterCondiciones.getItem(i);
                    }
                }
                break;
            case "ubicacion":
                for (int i = 0; i<adapterLocalidad.getCount(); i++){
                    if (adapterLocalidad.getItem(i).getDescripcionLocalidad().equals(filtro)){
                        if (pretendida){
                            ubicacion =adapterLocalidad.getItem(i);
                        }
                        else{
                            ubicacionPretendida =adapterLocalidad.getItem(i);
                        }
                    }
                }
            break;
        }

    }

    public void postEditarPublicacion(PublicacionEditarRequest publicacion){
        Call<ResponseBody> deleteRequest = service.update(idPublicacion, publicacion);
        deleteRequest.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Publicacion modificada con exito!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {}.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                    for (Error item: message) {
                        Toast.makeText(getApplicationContext(),item.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getCause()+ " \n"+t.getMessage());
                Toast.makeText(getApplicationContext(),"Error al modificar la publicación!", Toast.LENGTH_LONG).show();
            }
        });
    }


}