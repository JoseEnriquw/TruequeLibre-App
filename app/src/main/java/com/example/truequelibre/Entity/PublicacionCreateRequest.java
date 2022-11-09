package com.example.truequelibre.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PublicacionCreateRequest implements Serializable {
    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;
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
    @SerializedName("imagenes")
    @Expose
    private byte[] imagenes;

    public PublicacionCreateRequest() {
    }

    public PublicacionCreateRequest(Integer idUsuario, String nombre, String descripcion, Integer idCategoria, Integer idCategoriaPretendida, Integer idCondicion, Integer idUbicacion, Integer idUbicacionPretendida, byte[] imagenes) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idCategoriaPretendida = idCategoriaPretendida;
        this.idCondicion = idCondicion;
        this.idUbicacion = idUbicacion;
        this.idUbicacionPretendida = idUbicacionPretendida;
        this.imagenes = imagenes;
    }



    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public byte[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(byte[] imagenes) {
        this.imagenes = imagenes;
    }
}
