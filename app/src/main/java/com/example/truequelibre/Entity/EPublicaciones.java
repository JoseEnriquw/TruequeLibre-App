package com.example.truequelibre.Entity;

import com.example.truequelibre.EUsuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPublicaciones {
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    private String urlImg;
    private Integer idpublicacion;
    private ECategorias idcategoria;
    private ECategorias idcategoriapretendida;
    private EUsuario idusuario;
    private ECondicion idcondicion;
    private EEstado idestado;
    private ELocalidad idubicacion;
    private ELocalidad idubicacionpretendida;

    public EPublicaciones(String nombre, String descripcion, String urlImg, Integer idpublicacion,  EUsuario idusuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImg = urlImg;
        this.idpublicacion = idpublicacion;
        this.idusuario = idusuario;
    }

    public EPublicaciones(String nombre, String descripcion, String urlImg, Integer idpublicacion, ECategorias idcategoria, ECategorias idcategoriapretendida, EUsuario idusuario, ECondicion idcondicion, EEstado idestado, ELocalidad idubicacion, ELocalidad idubicacionpretendida) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImg = urlImg;
        this.idpublicacion = idpublicacion;
        this.idcategoria = idcategoria;
        this.idcategoriapretendida = idcategoriapretendida;
        this.idusuario = idusuario;
        this.idcondicion = idcondicion;
        this.idestado = idestado;
        this.idubicacion = idubicacion;
        this.idubicacionpretendida = idubicacionpretendida;
    }

    public Integer getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(Integer idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    public ECategorias getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(ECategorias idcategoria) {
        this.idcategoria = idcategoria;
    }

    public ECategorias getIdcategoriapretendida() {
        return idcategoriapretendida;
    }

    public void setIdcategoriapretendida(ECategorias idcategoriapretendida) {
        this.idcategoriapretendida = idcategoriapretendida;
    }

    public EUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(EUsuario idusuario) {
        this.idusuario = idusuario;
    }

    public ECondicion getIdcondicion() {
        return idcondicion;
    }

    public void setIdcondicion(ECondicion idcondicion) {
        this.idcondicion = idcondicion;
    }

    public EEstado getIdestado() {
        return idestado;
    }

    public void setIdestado(EEstado idestado) {
        this.idestado = idestado;
    }

    public ELocalidad getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(ELocalidad idubicacion) {
        this.idubicacion = idubicacion;
    }

    public ELocalidad getIdubicacionpretendida() {
        return idubicacionpretendida;
    }

    public void setIdubicacionpretendida(ELocalidad idubicacionpretendida) {
        this.idubicacionpretendida = idubicacionpretendida;
    }

    public EPublicaciones() {
    }

    public EPublicaciones(String nombre, String descripcion, String urlImg) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImg = urlImg;
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

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
