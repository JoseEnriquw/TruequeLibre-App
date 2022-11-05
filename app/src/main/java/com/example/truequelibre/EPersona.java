package com.example.truequelibre;

import java.util.Date;

public class EPersona {
    private String dni;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private ELocalidad localidad;
    private Date fecha_nacimiento;
    private Integer telefono;

    public EPersona(String dni, String nombre, String apellido) {
        this.dni = dni;
        Nombre = nombre;
        Apellido = apellido;
    }

    public EPersona(String dni, String nombre, String apellido, String direccion, ELocalidad localidad, Date fecha_nacimiento, Integer telefono) {
        this.dni = dni;
        Nombre = nombre;
        Apellido = apellido;
        Direccion = direccion;
        this.localidad = localidad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public ELocalidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(ELocalidad localidad) {
        this.localidad = localidad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
}
