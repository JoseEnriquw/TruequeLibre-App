package com.example.truequelibre.Entity;

public class EOferta {
    private EEstado estado;
    private EPublicaciones publicacionprincipal;
    private EPublicaciones ofertas;

    public EOferta(EEstado estado, EPublicaciones publicacionprincipal, EPublicaciones ofertas) {
        this.estado = estado;
        this.publicacionprincipal = publicacionprincipal;
        this.ofertas = ofertas;
    }

    public EOferta() {
    }

    public EEstado getEstado() {
        return estado;
    }

    public void setEstado(EEstado estado) {
        this.estado = estado;
    }

    public EPublicaciones getPublicacionprincipal() {
        return publicacionprincipal;
    }

    public void setPublicacionprincipal(EPublicaciones publicacionprincipal) {
        this.publicacionprincipal = publicacionprincipal;
    }

    public EPublicaciones getOfertas() {
        return ofertas;
    }

    public void setOfertas(EPublicaciones ofertas) {
        this.ofertas = ofertas;
    }
}
