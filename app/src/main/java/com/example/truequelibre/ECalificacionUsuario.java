package com.example.truequelibre;

import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Date;

public class ECalificacionUsuario {
    private Integer id;
    EUsuario eUsuario;
    RatingBar ratingBar;
    String comentarioMiPerfil;
    Date fechaComentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EUsuario geteUsuario() {
        return eUsuario;
    }

    public void seteUsuario(EUsuario eUsuario) {
        this.eUsuario = eUsuario;
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

    public ECalificacionUsuario(Integer id, EUsuario eUsuario, RatingBar ratingBar, String comentarioMiPerfil, Date fechaComentario) {
        this.id = id;
        this.eUsuario = eUsuario;
        this.ratingBar = ratingBar;
        this.comentarioMiPerfil = comentarioMiPerfil;
        this.fechaComentario = fechaComentario;
    }
    public ECalificacionUsuario(){

    }
}
