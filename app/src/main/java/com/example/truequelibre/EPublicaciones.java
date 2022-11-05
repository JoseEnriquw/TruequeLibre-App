package com.example.truequelibre;

import android.provider.ContactsContract;

public class EPublicaciones {
    private String titulo;
    private String subtitulo;
    private String urlImg;
    private Integer idpublicacion;
    private String Descripcion;
    private ECategorias idcategoria;
    private ECategorias idcategoriapretendida;
    private EUsuario idusuario;
    private ECondicion idcondicion;
    private EEstado idestado;
    private ELocalidad idubicacion;
    private ELocalidad idubicacionpretendida;

    public EPublicaciones(String titulo, String subtitulo, String urlImg, Integer idpublicacion, String descripcion, EUsuario idusuario) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.urlImg = urlImg;
        this.idpublicacion = idpublicacion;
        Descripcion = descripcion;
        this.idusuario = idusuario;
    }


    public EPublicaciones(String titulo, String subtitulo, String urlImg, Integer idpublicacion, String descripcion, ECategorias idcategoria, ECategorias idcategoriapretendida, EUsuario idusuario, ECondicion idcondicion, EEstado idestado, ELocalidad idubicacion, ELocalidad idubicacionpretendida) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.urlImg = urlImg;
        this.idpublicacion = idpublicacion;
        Descripcion = descripcion;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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

    public EPublicaciones(String titulo, String subtitulo, String urlImg) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.urlImg = urlImg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
