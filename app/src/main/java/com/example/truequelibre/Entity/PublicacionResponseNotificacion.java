package com.example.truequelibre.Entity;

public class PublicacionResponseNotificacion {
    private Integer id_usuario_principal;
    private String nombre_usuario_principal;
    private String imagen_usuario_principal;
    private Integer id_usuario_ofertante;
    private String nombre_usuario_ofertante;
    private String imagen_usuario_ofertante;

    public PublicacionResponseNotificacion(Integer id_usuario_principal, String nombre_usuario_principal, String imagen_usuario_principal, Integer id_usuario_ofertante, String nombre_usuario_ofertante, String imagen_usuario_ofertante) {
        this.id_usuario_principal = id_usuario_principal;
        this.nombre_usuario_principal = nombre_usuario_principal;
        this.imagen_usuario_principal = imagen_usuario_principal;
        this.id_usuario_ofertante = id_usuario_ofertante;
        this.nombre_usuario_ofertante = nombre_usuario_ofertante;
        this.imagen_usuario_ofertante = imagen_usuario_ofertante;
    }

    public PublicacionResponseNotificacion() {
    }

    public Integer getId_usuario_principal() {
        return id_usuario_principal;
    }

    public void setId_usuario_principal(Integer id_usuario_principal) {
        this.id_usuario_principal = id_usuario_principal;
    }

    public String getNombre_usuario_principal() {
        return nombre_usuario_principal;
    }

    public void setNombre_usuario_principal(String nombre_usuario_principal) {
        this.nombre_usuario_principal = nombre_usuario_principal;
    }

    public String getImagen_usuario_principal() {
        return imagen_usuario_principal;
    }

    public void setImagen_usuario_principal(String imagen_usuario_principal) {
        this.imagen_usuario_principal = imagen_usuario_principal;
    }

    public Integer getId_usuario_ofertante() {
        return id_usuario_ofertante;
    }

    public void setId_usuario_ofertante(Integer id_usuario_ofertante) {
        this.id_usuario_ofertante = id_usuario_ofertante;
    }

    public String getNombre_usuario_ofertante() {
        return nombre_usuario_ofertante;
    }

    public void setNombre_usuario_ofertante(String nombre_usuario_ofertante) {
        this.nombre_usuario_ofertante = nombre_usuario_ofertante;
    }

    public String getImagen_usuario_ofertante() {
        return imagen_usuario_ofertante;
    }

    public void setImagen_usuario_ofertante(String imagen_usuario_ofertante) {
        this.imagen_usuario_ofertante = imagen_usuario_ofertante;
    }
}
