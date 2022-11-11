package com.example.truequelibre.Entity;

public class OfertasResponse {
    Integer id_oferta;
    String nombre;
    String descripcion;
    String imagen;

    public OfertasResponse() {
    }

    public Integer getId_oferta() {
        return id_oferta;
    }

    public OfertasResponse(Integer id_oferta, String nombre, String descripcion, String imagen) {
        this.id_oferta = id_oferta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public void setId_oferta(Integer id_oferta) {
        this.id_oferta = id_oferta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
