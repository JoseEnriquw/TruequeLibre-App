package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private TextView tv_Registrarse;
    private TextView tvTruequeLibre;
    private EditText txtUsuario;
    private EditText txtContrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
            login();
        }
    }

    public boolean validarCampos(){
        boolean bnd = true;
        if(txtUsuario.length() == 0){
            txtUsuario.setError("Complete este campo!");
            bnd=false;
        }
        if(txtContrasenia.length() == 0){
            txtContrasenia.setError("Complete este campo!");
            bnd=false;
        }

        return bnd;
    }

    public void login(){

    }
}