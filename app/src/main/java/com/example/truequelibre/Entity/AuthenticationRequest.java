package com.example.truequelibre.Entity;

public class AuthenticationRequest {
    private String email;
    private String contrasenia;

    public AuthenticationRequest(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public AuthenticationRequest() {
    }

    public String getEMail() {
        return email;
    }

    public void setEMail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
