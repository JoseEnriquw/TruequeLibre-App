package com.example.truequelibre.Entity;

import android.widget.RatingBar;

import java.util.Date;

public class CalificacionUsuario {
    private Integer id;
    Usuario usuario;
    RatingBar ratingBar;
    String comentarioMiPerfil;
    Date fechaComentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario geteUsuario() {
        return usuario;
    }

    public void seteUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }

    public String getComentarioMiPerfil() {
        return comentarioMiPerfil;
    }

    public void setComentarioMiPerfil(String comentarioMiPerfil) {
        this.comentarioMiPerfil = comentarioMiPerfil;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public CalificacionUsuario(Integer id, Usuario usuario, RatingBar ratingBar, String comentarioMiPerfil, Date fechaComentario) {
        this.id = id;
        this.usuario = usuario;
        this.ratingBar = ratingBar;
        this.comentarioMiPerfil = comentarioMiPerfil;
        this.fechaComentario = fechaComentario;
    }
    public CalificacionUsuario(){

    }
}
