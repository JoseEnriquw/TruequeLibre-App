package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FiltrarOfertaRequest implements Serializable {
    @SerializedName("id_usuario")
    @Expose
    private Integer id_usuario;
    @SerializedName("estado")
    @Expose
    private String estado;

    public FiltrarOfertaRequest() {
    }

    public FiltrarOfertaRequest(Integer id_usuario, String estado) {
        this.id_usuario = id_usuario;
        this.estado = estado;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
