package com.example.truequelibre;

public class EEstado {
    private Integer id;
    private String Nombre;
    private String Descripcion;

    public EEstado() {
    }

    public EEstado(Integer id, String nombre, String descripcion) {
        this.id = id;
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
