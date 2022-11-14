package com.example.truequelibre.Entity;

public class OfertasResponse {

    String nombre_ofertante;
    String descripcion_ofertante;
    String imagen_ofertante;
    String nombre_principal;
    String descripcion_principal;
    String imagen_principal;
    Integer estado_id;
    Integer id_usuario_principal;
    boolean usuario_principal_acepto;
    boolean usuario_ofertante_acepto;
    Integer id;

    public OfertasResponse() {
    }

    public OfertasResponse(String nombre_ofertante, String descripcion_ofertante, String imagen_ofertante, String nombre_principal, String descripcion_principal, String imagen_principal, Integer estado_id, Integer id_usuario_principal, boolean usuario_principal_acepto, boolean usuario_ofertante_acepto, Integer id) {
        this.nombre_ofertante = nombre_ofertante;
        this.descripcion_ofertante = descripcion_ofertante;
        this.imagen_ofertante = imagen_ofertante;
        this.nombre_principal = nombre_principal;
        this.descripcion_principal = descripcion_principal;
        this.imagen_principal = imagen_principal;
        this.estado_id = estado_id;
        this.id_usuario_principal = id_usuario_principal;
        this.usuario_principal_acepto = usuario_principal_acepto;
        this.usuario_ofertante_acepto = usuario_ofertante_acepto;
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

    public Integer getId_usuario_principal() {
        return id_usuario_principal;
    }

    public void setId_usuario_principal(Integer id_usuario_principal) {
        this.id_usuario_principal = id_usuario_principal;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        estado_id = estado_id;
    }


    public String getNombre_ofertante() {
        return nombre_ofertante;
    }

    public void setNombre_ofertante(String nombre_ofertante) {
        this.nombre_ofertante = nombre_ofertante;
    }

    public String getDescripcion_ofertante() {
        return descripcion_ofertante;
    }

    public void setDescripcion_ofertante(String descripcion_ofertante) {
        this.descripcion_ofertante = descripcion_ofertante;
    }

    public String getImagen_ofertante() {
        return imagen_ofertante;
    }

    public void setImagen_ofertante(String imagen_ofertante) {
        this.imagen_ofertante = imagen_ofertante;
    }

    public String getNombre_principal() {
        return nombre_principal;
    }

    public void setNombre_principal(String nombre_principal) {
        this.nombre_principal = nombre_principal;
    }

    public String getDescripcion_principal() {
        return descripcion_principal;
    }

    public void setDescripcion_principal(String descripcion_principal) {
        this.descripcion_principal = descripcion_principal;
    }

    public String getImagen_principal() {
        return imagen_principal;
    }

    public void setImagen_principal(String imagen_principal) {
        this.imagen_principal = imagen_principal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
