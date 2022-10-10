package com.example.truequelibre;

public class EPublicaciones {
    private String titulo;
    private String subtitulo;
    private String urlImg;

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
