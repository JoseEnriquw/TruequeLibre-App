package com.example.truequelibre.Entity;

public class GetAllByCategoriaFilterRequest {

    private Integer categoria;
    private String search;
    private Integer usuario;

    public GetAllByCategoriaFilterRequest(Integer categoria, String search, Integer usuario) {
        this.categoria = categoria;
        this.search = search;
        this.usuario = usuario;
    }

    public GetAllByCategoriaFilterRequest() {
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
}
