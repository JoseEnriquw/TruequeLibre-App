package com.example.truequelibre;

import java.io.Serializable;

public class Categoria implements Serializable {
    private Integer id;
    private String descripcion;
   // private String urlImg;


    public Categoria() {
    }

    public Categoria(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIds(){
        String id = String.valueOf(getId());
        return id.hashCode();
    }
}
