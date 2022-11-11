package com.example.truequelibre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditarPublicaciones extends AppCompatActivity {

    EditText txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicaciones);

        txtTitulo = (EditText) findViewById(R.id.txtTituloEditar);
        Integer idPublicacion = (Integer) getIntent().getSerializableExtra("idPublicacion");
        txtTitulo.setText(String.valueOf(idPublicacion));
    }


}