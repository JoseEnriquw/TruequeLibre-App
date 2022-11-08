package com.example.truequelibre.Entity;

public class Oferta {
    private Estado estado;
    private Publicacion publicacionprincipal;
    private Publicacion ofertas;

    public Oferta(Estado estado, Publicacion publicacionprincipal, Publicacion ofertas) {
        this.estado = estado;
        this.publicacionprincipal = publicacionprincipal;
        this.ofertas = ofertas;
    }

    public Oferta() {
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Publicacion getPublicacionprincipal() {
        return publicacionprincipal;
    }

    public void setPublicacionprincipal(Publicacion publicacionprincipal) {
        this.publicacionprincipal = publicacionprincipal;
    }

    public Publicacion getOfertas() {
        return ofertas;
    }

    public void setOfertas(Publicacion ofertas) {
        this.ofertas = ofertas;
    }
}
