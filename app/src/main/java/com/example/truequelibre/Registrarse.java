package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Registrarse extends AppCompatActivity {

    private TextView tv_IniciarSesion;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtFechaNacimiento;
    private EditText txtDNI;
    private EditText txtCorreo;
    private EditText txtTelefono;
    private EditText txtDireccion;
    private EditText txtLocalidad;
    private EditText txtContrasenia;
    private EditText txtConfirmarContrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
        txtDNI = (EditText) findViewById(R.id.txtDNI);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtLocalidad = (EditText) findViewById(R.id.txtLocalidad);
        txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);
        txtConfirmarContrasenia = (EditText) findViewById(R.id.txtConfirmarContrasenia);

        tv_IniciarSesion = (TextView)findViewById(R.id.tvIniciarSesion);

        tv_IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

    }

    public void btnRegistrarse(View view){
        if (validarCampos()){

        };
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

        return bnd;
    }

}