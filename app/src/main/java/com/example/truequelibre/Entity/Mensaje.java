package com.example.truequelibre.Entity;

public class Mensaje {
    private String mensaje;
    private Integer usuario;
    private String fotoUsuario;

    public Mensaje(String mensaje, Integer usuario) {
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public Mensaje(String mensaje, Integer usuario, String fotoUsuario) {
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.fotoUsuario = fotoUsuario;
    }

    public Mensaje() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
}
