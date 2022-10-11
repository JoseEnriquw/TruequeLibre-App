package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

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
            if (usuario.equals("admin") && contrasenia.equals("admin")){
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
            }
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