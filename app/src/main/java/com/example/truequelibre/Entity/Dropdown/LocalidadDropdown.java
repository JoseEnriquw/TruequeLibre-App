package com.example.truequelibre.Entity.Dropdown;

public class LocalidadDropdown {
    private Integer idLocalidad;
    private String descripcionLocalidad;

    public LocalidadDropdown() {
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getDescripcionLocalidad() {
        return descripcionLocalidad;
    }

    public void setDescripcionLocalidad(String descripcionLocalidad) {
        this.descripcionLocalidad = descripcionLocalidad;
    }

    @Override
    public String toString() {
        return descripcionLocalidad ;
    }
}
