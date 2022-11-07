package com.example.truequelibre;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private String direccion;
    private Date fechaNacimiento;
    private Integer telefono;
    private Localidad localidad;
    private String id;

    public Persona(String dni, String nombre, String apellido) {
        this.id = dni;
        nombre = nombre;
        apellido = apellido;
    }

    public Persona() {
    }

    public Persona(String nombre, String apellido, String direccion, Date fechaNacimiento, Integer telefono, Localidad localidad, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.localidad = localidad;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
