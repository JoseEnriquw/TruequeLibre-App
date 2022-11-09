package com.example.truequelibre.Entity.Dropdown;

import java.util.List;

public class PublicacionDropdown {
    private List<CategoriaDropdown> categorias;
    private List<CondicionDropdown> condiciones;
    private List<LocalidadDropdown> localidades;

    public PublicacionDropdown() {
    }

    public List<CategoriaDropdown> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDropdown> categorias) {
        this.categorias = categorias;
    }

    public List<CondicionDropdown> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(List<CondicionDropdown> condiciones) {
        this.condiciones = condiciones;
    }

    public List<LocalidadDropdown> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<LocalidadDropdown> localidades) {
        this.localidades = localidades;
    }
}
