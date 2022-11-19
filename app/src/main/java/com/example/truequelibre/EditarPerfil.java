package com.example.truequelibre;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.PublicacionCreateRequest;
import com.example.truequelibre.Entity.UpdatePersonaVM;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.IPersonaService;
import com.example.truequelibre.Utils.IPublicacionService;
import com.example.truequelibre.Utils.IUsuarioService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPerfil extends AppCompatActivity {
String Nombre;
String Imagen;
Integer Id;
TextView etNombre;
ImageView etfoto;
ImageView imageViewarray[]=new ImageView[1];
Button btneditar;
IUsuarioService serviceuser;
IPersonaService service;
Context contex;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        Id=getIntent().getIntExtra("idUsuario",0);


      Nombre=getIntent().getStringExtra("Nombre");
       Imagen=getIntent().getStringExtra("Imagen");
        service= Apis.getPersonaService();

        etNombre= findViewById(R.id.editarnombre);
        etfoto=findViewById(R.id.editarimagen);
        btneditar=findViewById(R.id.btneditarimagenperfil);
        imageViewarray[0] = etfoto;
        contex=this;
        etNombre.setText(Nombre);
        if (Imagen!= null){
            byte[] byteArray =  Base64.decode(Imagen, Base64.DEFAULT);
            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            etfoto.setImageBitmap(theImage);
        }

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] imageInByte = convertImgViewToArray();
                UpdatePersonaVM request = new UpdatePersonaVM(imageInByte);
                PostEditarFotoPerfil(request);
            }
        });

        etfoto.setOnClickListener(view -> mGetContet.launch("image/*"));
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

    public boolean PostEditarFotoPerfil(UpdatePersonaVM request){

        Call<Void> call = service.updatePersona(Id, request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    Intent intent = new Intent(contex,MainActivity.class);
                    intent.putExtra("idUsuario", Id);
                    contex.startActivity(intent);
                }
                else{
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {}.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                    for (Error item: message) {

                    }
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
           }
        });
        return true;
    }

    public byte[] convertImgViewToArray(){
        try{
            if (etfoto.getDrawable() != null){
                Bitmap bitmap = ((BitmapDrawable) etfoto.getDrawable()).getBitmap();
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



}