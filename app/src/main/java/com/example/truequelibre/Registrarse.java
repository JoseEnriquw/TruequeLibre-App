package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequelibre.Entity.Dropdown.LocalidadDropdown;
import com.example.truequelibre.Entity.Dropdown.PublicacionDropdown;
import com.example.truequelibre.Entity.Dropdown.UsuarioDropdown;
import com.example.truequelibre.Entity.PublicacionCreateRequest;
import com.example.truequelibre.Entity.UsuarioCreateRequest;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.IPublicacionService;
import com.example.truequelibre.Utils.IUsuarioService;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrarse extends AppCompatActivity {

    private TextView tv_IniciarSesion;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtFechaNacimiento;
    private EditText txtDNI;
    private EditText txtCorreo;
    private EditText txtTelefono;
    private EditText txtDireccion;
    private EditText txtContrasenia;
    private EditText txtConfirmarContrasenia;
    ArrayAdapter<LocalidadDropdown> adapterLocalidad;
    AutoCompleteTextView dropDownLocalidades;
    LocalidadDropdown ubicacion;
    IUsuarioService service;
    UsuarioDropdown lista= new UsuarioDropdown();
    UsuarioCreateRequest usuarionuevo = new UsuarioCreateRequest();
    Button botonLogin;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        context = this;
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        txtDNI = (EditText) findViewById(R.id.txtDNI);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);
        txtConfirmarContrasenia = (EditText) findViewById(R.id.txtConfirmarContrasenia);

        tv_IniciarSesion = (TextView)findViewById(R.id.tvIniciarSesion);

        botonLogin = findViewById(R.id.btnLogin);


        tv_IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        service= Apis.getUsuarioService();
        Call<UsuarioDropdown> call =service.getUsuarioDropdown();

        call.enqueue(new Callback<UsuarioDropdown>() {
            @Override
            public void onResponse(Call<UsuarioDropdown> call, retrofit2.Response<UsuarioDropdown> response) {
                if(response.isSuccessful()) {
                    lista = response.body();
                    adapterLocalidad = new ArrayAdapter<>(getApplicationContext(), R.layout.drop_down_item, lista.getLocalidades());
                    dropDownLocalidades =(AutoCompleteTextView) findViewById(R.id.txtlocalidad);
                    dropDownLocalidades.setAdapter(adapterLocalidad);

                    dropDownLocalidades.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ubicacion = adapterLocalidad.getItem(position);
                        }
                    });


                } else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {
                    }.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(getApplicationContext(), item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<UsuarioDropdown> call, Throwable t) {
                System.out.println(lista);
            }
        });

    }

    public void btnRegistrarse(View view){
        if (validarCampos()){
            if(txtConfirmarContrasenia.getText().toString().equals(txtContrasenia.getText().toString())){
                try {
                    RellenarCampos(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                postUsuarioCreate(usuarionuevo);

            }
        };
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean validarCampos(){
        boolean bnd = true;
        LinearLayout llyParent = findViewById(R.id.linearLayoutRegistrarse);
        int count = llyParent.getChildCount();
        for (int i=0;i<count;i++){
            if (llyParent.getChildAt(i) instanceof TextInputLayout){
                TextInputLayout layout = (TextInputLayout) llyParent.getChildAt(i);
                FrameLayout frameLayout = (FrameLayout) layout.getChildAt(0);
                int id = frameLayout.getChildAt(0).getId();
                EditText et = ((EditText) findViewById(id));
                if (et.length() == 0){
                    layout.setError("Complete este campo!");
                    bnd = false;
                }
            }
        }

        if (!validarEmail(txtCorreo.getText().toString())){
            TextInputLayout layout = findViewById(R.id.email_text_input_layout);
            layout.setError("El correo tiene un formato incorrecto!");
            bnd = false;
        }

        return bnd;
    }

    public void RellenarCampos(View v) throws ParseException {
        usuarionuevo.setNombre(String.valueOf(txtNombre.getText()));
        usuarionuevo.setApellido(String.valueOf(txtApellido.getText()));
     //   SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      //   DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         // 03/02/1989
      //  Date fecha = formatter.parse(String.valueOf(txtFechaNacimiento.getText()));

        String startDateString = String.valueOf(txtFechaNacimiento.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        String fecha = sdf2.format(sdf.parse(startDateString));
        usuarionuevo.setFechaNacimiento(fecha);
        usuarionuevo.setMail(String.valueOf(txtCorreo.getText()));
        usuarionuevo.setDni(String.valueOf(txtDNI.getText()));
        usuarionuevo.setTelefono(String.valueOf(txtTelefono.getText()));
        usuarionuevo.setDireccion(String.valueOf(txtDireccion.getText()));
        usuarionuevo.setContrasenia(String.valueOf(txtConfirmarContrasenia.getText()));
        usuarionuevo.setLocalidad(ubicacion.getIdLocalidad());

    }

    public boolean postUsuarioCreate(UsuarioCreateRequest usuario){
        service= Apis.getUsuarioService();
        Call<Void> call = service.create(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){

                        Toast.makeText(Registrarse.this,"Usuario creado con exito!", Toast.LENGTH_LONG).show();
                   //
                    Intent intent= new Intent(getApplicationContext(),Login.class);
                    context.startActivity(intent);
                }
                else {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Error>>() {
                    }.getType();
                    List<Error> message = gson.fromJson(response.errorBody().charStream(), type);

                    for (Error item : message) {
                        Toast.makeText(getApplicationContext(), item.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(Registrarse.this,"Error al crear usuario!", Toast.LENGTH_LONG).show();
            }
        });
        return true;
    }

}