package com.example.truequelibre.Entity;

public class EProvincia extends EPais{
    private Integer idprovincia;
    private String Nombre;
    private EPais pais;

    public EProvincia(Integer idpais, String nombre) {
        super(idpais, nombre);
    }

    public Integer getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    @Override
    public String getNombre() {
        return Nombre;
    }

    @Override
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public EPais getPais() {
        return pais;
    }

    public void setPais(EPais pais) {
        this.pais = pais;
    }
}
