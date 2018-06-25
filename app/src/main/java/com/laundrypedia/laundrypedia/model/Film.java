package com.laundrypedia.laundrypedia.model;

public class Film {
    String judul, genre, tahun;

    public Film() {
    }

    public Film(String judul, String genre, String tahun) {
        this.judul = judul;
        this.genre = genre;
        this.tahun = tahun;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
