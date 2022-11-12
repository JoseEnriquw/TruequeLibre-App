package com.example.truequelibre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PublicacionEditarRequestAdmin implements Serializable {
    @SerializedName("idPublicacion")
    @Expose
    private Integer idPublicacion;
    @SerializedName("idEstado")
    @Expose
    private Integer idEstado;

    public PublicacionEditarRequestAdmin() {
    }

    public PublicacionEditarRequestAdmin(Integer idPublicacion, Integer idEstado) {
        this.idPublicacion = idPublicacion;
        this.idEstado = idEstado;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}
