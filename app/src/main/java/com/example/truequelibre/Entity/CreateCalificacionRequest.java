package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateCalificacionRequest implements Serializable {
    @SerializedName("idUsuario")
    @Expose
    Integer idUsuarioCalificador;
    @SerializedName("idUsuario")
    @Expose
    Integer idUsuarioCalificado;
    @SerializedName("idUsuario")
    @Expose
    short estrellas;
    @SerializedName("idUsuario")
    @Expose
    String comentario;

    public CreateCalificacionRequest() {
    }

    public CreateCalificacionRequest(Integer idUsuarioCalificador, Integer idUsuarioCalificado, short estrellas, String comentario) {
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

    public short getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(short estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
