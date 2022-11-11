package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PublicacionEditarRequest implements Serializable {

    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("idCategoria")
    @Expose
    private Integer idCategoria;
    @SerializedName("idCategoriaPretendida")
    @Expose
    private Integer idCategoriaPretendida;
    @SerializedName("idCondicion")
    @Expose
    private Integer idCondicion;
    @SerializedName("idUbicacion")
    @Expose
    private Integer idUbicacion;
    @SerializedName("idUbicacionPretendida")
    @Expose
    private Integer idUbicacionPretendida;
    @SerializedName("idEstado")
    @Expose
    private Integer idEstado;
    @SerializedName("imagenes")
    @Expose
    private byte[] imagenes;

    public PublicacionEditarRequest() {
    }

    public PublicacionEditarRequest(String nombre, String descripcion, Integer idCategoria, Integer idCategoriaPretendida, Integer idCondicion,Integer idEstado, Integer idUbicacion, Integer idUbicacionPretendida, byte[] imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idCategoriaPretendida = idCategoriaPretendida;
        this.idCondicion = idCondicion;
        this.idUbicacion = idUbicacion;
        this.idUbicacionPretendida = idUbicacionPretendida;
        this.imagenes = imagenes;
        this.idEstado = idEstado;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoriaPretendida() {
        return idCategoriaPretendida;
    }

    public void setIdCategoriaPretendida(Integer idCategoriaPretendida) {
        this.idCategoriaPretendida = idCategoriaPretendida;
    }

    public Integer getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Integer getIdUbicacionPretendida() {
        return idUbicacionPretendida;
    }

    public void setIdUbicacionPretendida(Integer idUbicacionPretendida) {
        this.idUbicacionPretendida = idUbicacionPretendida;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public byte[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(byte[] imagenes) {
        this.imagenes = imagenes;
    }
}
