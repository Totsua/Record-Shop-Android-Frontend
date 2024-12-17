package com.northcoders.record_shop_android_frontend.model;


public class Album {

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
}
