package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateOfertaVM implements Serializable {
    @SerializedName("id_estado")
    @Expose
    Integer id_estado;

    public UpdateOfertaVM() {
    }

    public UpdateOfertaVM(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }
}
