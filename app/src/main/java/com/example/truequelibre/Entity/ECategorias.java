package com.example.truequelibre.Entity;

import android.net.Uri;

public class ECategorias {
    private String titulo;
    private String urlImg;
    private int id;

    public ECategorias() {
    }

    public ECategorias(String titulo, String urlImg) {
        this.titulo = titulo;
        this.urlImg = urlImg;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlImg() {
        return this.urlImg ;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getIds(){
        String id = String.valueOf(getId());
        return id.hashCode();
    }
}
