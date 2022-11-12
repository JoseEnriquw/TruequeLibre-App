package com.example.truequelibre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PublicacionEditarRequestAdmin implements Serializable {

    @SerializedName("idEstado")
    @Expose
    private Integer idEstado;

    public PublicacionEditarRequestAdmin() {
    }

    public PublicacionEditarRequestAdmin( Integer idEstado) {

        this.idEstado = idEstado;
    }





    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
}
