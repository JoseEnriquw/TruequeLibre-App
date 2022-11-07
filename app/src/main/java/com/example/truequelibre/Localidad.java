package com.example.truequelibre;

import java.io.Serializable;

public class Localidad extends Provincia implements Serializable {
    private Integer id;
    private String nombre;
    private Provincia provincia;

    public Localidad() {
    }

    public Localidad(Integer id, String nombre, Provincia provincia) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public Localidad(Integer id, String nombre, Pais pais, Integer id1, String nombre1, Provincia provincia) {
        super(id, nombre, pais);
        this.id = id1;
        this.nombre = nombre1;
        this.provincia = provincia;
    }

    public Localidad(Integer id, String nombre, Integer id1, String nombre1, Pais pais, Integer id2, String nombre2, Provincia provincia) {
        super(id, nombre, id1, nombre1, pais);
        this.id = id2;
        this.nombre = nombre2;
        this.provincia = provincia;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
