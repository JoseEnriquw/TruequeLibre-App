package com.example.truequelibre;

public class Provincia extends Pais {
    private Integer id;
    private String nombre;
    private Pais pais;

    public Provincia() {
    }

    public Provincia(Integer id, String nombre, Pais pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Provincia(Integer id, String nombre, Integer id1, String nombre1, Pais pais) {
        super(id, nombre);
        this.id = id1;
        this.nombre = nombre1;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
