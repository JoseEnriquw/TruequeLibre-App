package com.example.truequelibre;

public class ECondicion {
    private Integer idcondicion;
    private String Nombre;
    private String descripcion;

    public ECondicion(Integer idcondicion, String nombre, String descripcion) {
        this.idcondicion = idcondicion;
        Nombre = nombre;
        this.descripcion = descripcion;
    }

    public ECondicion() {
    }

    public Integer getIdcondicion() {
        return idcondicion;
    }

    public void setIdcondicion(Integer idcondicion) {
        this.idcondicion = idcondicion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
