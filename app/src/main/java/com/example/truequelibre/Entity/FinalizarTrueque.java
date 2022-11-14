package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FinalizarTrueque implements Serializable {
    @SerializedName("id")
    @Expose
    Integer id;
    @SerializedName("usuario_principal_acepto")
    @Expose
    boolean usuario_principal_acepto;
    @SerializedName("usuario_ofertante_acepto")
    @Expose
    boolean usuario_ofertante_acepto;

    public FinalizarTrueque() {
    }

    public FinalizarTrueque(Integer id, boolean usuario_principal_acepto, boolean usuario_ofertante_acepto) {
        this.id = id;
        this.usuario_principal_acepto = usuario_principal_acepto;
        this.usuario_ofertante_acepto = usuario_ofertante_acepto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isUsuario_principal_acepto() {
        return usuario_principal_acepto;
    }

    public void setUsuario_principal_acepto(boolean usuario_principal_acepto) {
        this.usuario_principal_acepto = usuario_principal_acepto;
    }

    public boolean isUsuario_ofertante_acepto() {
        return usuario_ofertante_acepto;
    }

    public void setUsuario_ofertante_acepto(boolean usuario_ofertante_acepto) {
        this.usuario_ofertante_acepto = usuario_ofertante_acepto;
    }
}
