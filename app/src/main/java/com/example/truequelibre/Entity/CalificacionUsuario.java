package com.example.truequelibre.Entity;

import android.widget.RatingBar;

import java.util.Date;

public class CalificacionUsuario {
    private String nombreApellido;
    private String imagen;
    private String comentario;
    private float estrellas;
    private Date fecha;

    public CalificacionUsuario(){

    }

    public CalificacionUsuario(String nombreApellido, String imagen, String comentario, float estrellas, Date fecha) {
        this.nombreApellido = nombreApellido;
        this.imagen = imagen;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fecha = fecha;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
