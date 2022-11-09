package com.example.truequelibre.Entity.Dropdown;

public class CategoriaDropdown {
    private Integer idCategoria;
    private String descripcionCategoria;

    public CategoriaDropdown() {
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    @Override
    public String toString() {
        return descripcionCategoria ;
    }
}
