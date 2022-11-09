package com.example.truequelibre.Entity.Dropdown;

public class CondicionDropdown {
    private Integer idCondicion;
    private String descripcionCondicion;

    public CondicionDropdown() {
    }

    public Integer getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getDescripcionCondicion() {
        return descripcionCondicion;
    }

    public void setDescripcionCondicion(String descripcionCondicion) {
        this.descripcionCondicion = descripcionCondicion;
    }

    @Override
    public String toString() {
        return descripcionCondicion ;
    }
}
