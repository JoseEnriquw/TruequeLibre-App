package com.example.truequelibre;

public class Estado {
    private Integer id;
    private String Nombre;


    public Estado() {
    }

    public Estado(Integer id, String nombre) {
        this.id = id;
        Nombre = nombre;
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


}
