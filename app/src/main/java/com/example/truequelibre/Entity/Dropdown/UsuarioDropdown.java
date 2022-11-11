package com.example.truequelibre.Entity.Dropdown;

import java.util.List;

public class UsuarioDropdown {
    private List<LocalidadDropdown> localidades;

    public UsuarioDropdown() {
    }

    public UsuarioDropdown(List<LocalidadDropdown> localidades) {
        this.localidades = localidades;
    }

    public List<LocalidadDropdown> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<LocalidadDropdown> localidades) {
        this.localidades = localidades;
    }
}
