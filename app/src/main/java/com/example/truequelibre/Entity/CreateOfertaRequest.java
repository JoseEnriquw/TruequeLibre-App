package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateOfertaRequest implements Serializable  {
    @SerializedName("id_publicacion_principal")
    @Expose
    Integer id_publicacion_principal;
    @SerializedName("id_publicacion_ofertante")
    @Expose
    Integer id_publicacion_ofertante;

    public CreateOfertaRequest(Integer id_publicacion_principal, Integer id_publicacion_ofertante) {
        this.id_publicacion_principal = id_publicacion_principal;
        this.id_publicacion_ofertante = id_publicacion_ofertante;
    }

    public CreateOfertaRequest() {
    }

    public Integer getId_publicacion_principal() {
        return id_publicacion_principal;
    }

    public void setId_publicacion_principal(Integer id_publicacion_principal) {
        this.id_publicacion_principal = id_publicacion_principal;
    }

    public Integer getId_publicacion_ofertante() {
        return id_publicacion_ofertante;
    }

    public void setId_publicacion_ofertante(Integer id_publicacion_ofertante) {
        this.id_publicacion_ofertante = id_publicacion_ofertante;
    }
}
