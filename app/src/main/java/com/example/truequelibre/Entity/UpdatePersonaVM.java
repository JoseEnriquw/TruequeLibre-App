package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdatePersonaVM implements Serializable {
    @SerializedName("imagen")
    @Expose
    byte[] imagen;

    public UpdatePersonaVM( byte[] imagen) {
        this.imagen = imagen;
    }

    public UpdatePersonaVM() {
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
