package com.example.truequelibre;

public class Publicacion {

    private Integer id;
    private Usuario usuario;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private Categoria categoriapretendida;
    private String imagenes;
    private Condicion condicion;
    private Localidad ubicacion;
    private Localidad ubicacionpretendida;
    private Estado estado;

    public Publicacion() {
    }

    public Publicacion(Integer id, Usuario usuario, String nombre, String descripcion, Categoria categoria, Categoria categoriapretendida, String imagenes, Condicion condicion) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.categoriapretendida = categoriapretendida;
        this.imagenes = imagenes;
        this.condicion = condicion;
    }

    public Publicacion(Integer id, Usuario usuario, String nombre, String descripcion, Categoria categoria, Categoria categoriapretendida, String imagenes, Condicion condicion, Localidad ubicacion, Localidad ubicacionpretendida, Estado estado) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.categoriapretendida = categoriapretendida;
        this.imagenes = imagenes;
        this.condicion = condicion;
        this.ubicacion = ubicacion;
        this.ubicacionpretendida = ubicacionpretendida;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoriapretendida() {
        return categoriapretendida;
    }

    public void setCategoriapretendida(Categoria categoriapretendida) {
        this.categoriapretendida = categoriapretendida;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
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

    public Localidad getUbicacionpretendida() {
        return ubicacionpretendida;
    }

    public void setUbicacionpretendida(Localidad ubicacionpretendida) {
        this.ubicacionpretendida = ubicacionpretendida;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
