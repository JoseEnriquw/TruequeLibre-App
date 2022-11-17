package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateCalificacionRequest implements Serializable {
    @SerializedName("idUsuarioCalificador")
    @Expose
    Integer idUsuarioCalificador;
    @SerializedName("idUsuarioCalificado")
    @Expose
    Integer idUsuarioCalificado;
    @SerializedName("estrellas")
    @Expose
    float estrellas;
    @SerializedName("comentario")
    @Expose
    String comentario;

    public CreateCalificacionRequest() {
    }

    public CreateCalificacionRequest(Integer idUsuarioCalificador, Integer idUsuarioCalificado, float estrellas, String comentario) {
        this.idUsuarioCalificador = idUsuarioCalificador;
        this.idUsuarioCalificado = idUsuarioCalificado;
        this.estrellas = estrellas;
        this.comentario = comentario;
    }

    public Integer getIdUsuarioCalificador() {
        return idUsuarioCalificador;
    }

    public void setIdUsuarioCalificador(Integer idUsuarioCalificador) {
        this.idUsuarioCalificador = idUsuarioCalificador;
    }

    public Integer getIdUsuarioCalificado() {
        return idUsuarioCalificado;
    }

    public void setIdUsuarioCalificado(Integer idUsuarioCalificado) {
        this.idUsuarioCalificado = idUsuarioCalificado;
    }

    public float getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
