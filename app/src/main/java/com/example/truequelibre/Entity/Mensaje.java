package com.example.truequelibre.Entity;

public class Mensaje {
    private String mensaje;
    private String usuario;

    public Mensaje(String mensaje, String usuario) {
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public Mensaje() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
