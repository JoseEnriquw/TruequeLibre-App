package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllByCategoriaRequest {
    @SerializedName("categoria")
    @Expose
    private Integer categoria;
    @SerializedName("usuario")
    @Expose
    private Integer usuario;

    public GetAllByCategoriaRequest() {
    }

    public GetAllByCategoriaRequest(Integer categoria, Integer usuario) {
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
}
