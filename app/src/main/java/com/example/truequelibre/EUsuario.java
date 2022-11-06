package com.example.truequelibre;

import com.example.truequelibre.Entity.EPersona;

public class EUsuario {
    private Integer idusuario;
    private EPersona DNI;
    private String email;
    private String contrasenia;
    private Boolean estado;
    private String urlImg;

    public EUsuario(Integer idusuario, EPersona DNI, String email, String contrasenia, Boolean estado, String urlImg) {
        this.idusuario = idusuario;
        this.DNI = DNI;
        this.email = email;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.urlImg = urlImg;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public EUsuario(Integer idusuario, EPersona DNI, String email, String contrasenia, Boolean estado) {
        this.idusuario = idusuario;
        this.DNI = DNI;
        this.email = email;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public EPersona getDNI() {
        return DNI;
    }

    public void setDNI(EPersona DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
