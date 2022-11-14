package com.example.truequelibre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateFinalizarTruequeRequest implements Serializable {
    @SerializedName("idOferta")
    @Expose
    Integer idOferta;
    @SerializedName("usuario_principal_acepto")
    @Expose
    boolean usuario_principal_acepto;
    @SerializedName("usuario_ofertante_acepto")
    @Expose
    boolean usuario_ofertante_acepto;

    public CreateFinalizarTruequeRequest() {
    }

    public CreateFinalizarTruequeRequest(Integer idOferta, boolean usuario_principal_acepto, boolean usuario_ofertante_acepto) {
        this.idOferta = idOferta;
        this.usuario_principal_acepto = usuario_principal_acepto;
        this.usuario_ofertante_acepto = usuario_ofertante_acepto;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
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
