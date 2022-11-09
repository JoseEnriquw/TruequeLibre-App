package com.example.truequelibre.Entity;

public class PublicacionCreate {
    private Integer id;
    private String nombre;
    private String descripcion;
    private byte[] imagenes;
    private Usuario usuario;
    private Condicion condicion;
    private Localidad ubicacion;
    private Localidad ubicacionPretendida;
    private Categoria interes;
    private Categoria categoria;

    public PublicacionCreate() {
    }

    public PublicacionCreate(String nombre, String descripcion, Usuario usuario, Condicion condicion, Localidad ubicacion, Localidad ubicacionPretendida, Categoria interes, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.condicion = condicion;
        this.ubicacion = ubicacion;
        this.ubicacionPretendida = ubicacionPretendida;
        this.interes = interes;
        this.categoria = categoria;
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

    public byte[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(byte[] imagenes) {
        this.imagenes = imagenes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public Localidad getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Localidad ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Localidad getUbicacionPretendida() {
        return ubicacionPretendida;
    }

    public void setUbicacionPretendida(Localidad ubicacionPretendida) {
        this.ubicacionPretendida = ubicacionPretendida;
    }

    public Categoria getInteres() {
        return interes;
    }

    public void setInteres(Categoria interes) {
        this.interes = interes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
