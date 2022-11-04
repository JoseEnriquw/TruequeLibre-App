package com.example.truequelibre;

public class ELocalidad extends EProvincia{
    private Integer idlocalidad;
    private String Nombre;
    private EProvincia provincia;



    public ELocalidad(Integer idpais, String nombre) {
        super(idpais, nombre);
    }

    public Integer getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(Integer idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    @Override
    public String getNombre() {
        return Nombre;
    }

    @Override
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public EProvincia getProvincia() {
        return provincia;
    }

    public void setProvincia(EProvincia provincia) {
        this.provincia = provincia;
    }
}
