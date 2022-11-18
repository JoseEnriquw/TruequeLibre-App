package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdatePersonaVM implements Serializable {
    @SerializedName("id_usuario")
    @Expose
    Integer id_usuario;
    @SerializedName("imagen")
    @Expose
    byte[] imagen;

    public UpdatePersonaVM(Integer id_usuario, byte[] imagen) {
        this.id_usuario = id_usuario;
        this.imagen = imagen;
    }
}
