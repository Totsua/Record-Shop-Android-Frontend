package com.northcoders.record_shop_android_frontend.model;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.northcoders.record_shop_android_frontend.BR;

public class Album extends BaseObservable {

    long id;
    String name;
    Artist artist;
    String genre;
    String releaseDate;
    int stock;
    double price;

    public Album() {}

    public Album(long id, String name, Artist artist, String genre, String releaseDate, int stock, double price) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.stock = stock;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
