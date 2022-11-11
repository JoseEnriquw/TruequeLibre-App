package com.example.truequelibre.Entity;

public class PublicacionResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagenes;
    private String categoria;
    private UsuarioPublicacion usuario;
    private String condicion;
    private String ubicacion;
    private String ubicacionPretendida;
    private String interes;

    public PublicacionResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public UsuarioPublicacion getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPublicacion usuario) {
        this.usuario = usuario;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacionPretendida() {
        return ubicacionPretendida;
    }

    public void setUbicacionPretendida(String ubicacionPretendida) {
        this.ubicacionPretendida = ubicacionPretendida;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }
}
