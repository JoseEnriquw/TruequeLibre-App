package com.example.truequelibre;

public class Usuario {
    private Integer id;
    private String mail;
    private String contrasenia;
    private Estado estado;
    private Persona persona;

    public Usuario(Integer id, String mail, String contrasenia, Estado estado, Persona persona) {
        this.id = id;
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.persona = persona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
