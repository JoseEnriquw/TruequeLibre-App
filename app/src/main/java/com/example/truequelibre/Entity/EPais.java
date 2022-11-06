package com.example.truequelibre.Entity;

public class EPais {
    private Integer idpais;
    private String Nombre;

    public Integer getIdpais() {
        return idpais;
    }

    public void setIdpais(Integer idpais) {
        this.idpais = idpais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public EPais(Integer idpais, String nombre) {
        this.idpais = idpais;
        Nombre = nombre;
    }


}
