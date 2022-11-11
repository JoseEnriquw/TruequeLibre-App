package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.truequelibre.Entity.AuthenticationRequest;
import com.example.truequelibre.Entity.Usuario;
import com.example.truequelibre.Utils.Apis;
import com.example.truequelibre.Utils.Error;
import com.example.truequelibre.Utils.IUsuarioService;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {

    private TextView tv_Registrarse;
    private TextView tvTruequeLibre;
    private EditText txtUsuario;
    private EditText txtContrasenia;
    IUsuarioService service;
    Integer idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        service= Apis.getUsuarioService();

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);

        tv_Registrarse = (TextView)findViewById(R.id.tvRegistrarse);
        tv_Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registrarse.class);
                startActivity(i);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.shake_animation);
        tvTruequeLibre = (TextView) findViewById(R.id.tvTruequeLibre);
        tvTruequeLibre.startAnimation(animation);
    }

    public void btnLogin(View view){
        if (validarCampos()){
            String usuario = String.valueOf(txtUsuario.getText());
            String contrasenia = String.valueOf(txtContrasenia.getText());
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);

            Call<Integer> call =service.authentication(new AuthenticationRequest(usuario,contrasenia));

            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, retrofit2.Response<Integer> response) {
                    if(response.isSuccessful()) {
                        idUsuario = response.body();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("idUsuario", idUsuario);
                        startActivity(i);

                    }
                    else
                    {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Error>>() {}.getType();
                        List<Error> message = gson.fromJson(response.errorBody().charStream(),type);

                        for (Error item: message) {
                        toast.setText(item.getMessage());
                        toast.show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    System.out.println(t.getCause()+"\n"+t.getMessage());
                    toast.setText(t.getCause()+"\n"+t.getMessage());
                    toast.show();
                }
            });
        }
    }

    public boolean validarCampos(){
        boolean bnd = true;
        LinearLayout llyParent = findViewById(R.id.linearLayoutLogin);
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

        return bnd;
    }

    public void login(){

    }
}