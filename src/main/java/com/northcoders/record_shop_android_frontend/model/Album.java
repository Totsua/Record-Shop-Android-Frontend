package com.northcoders.record_shop_android_frontend.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.northcoders.record_shop_android_frontend.BR;

public class Album extends BaseObservable implements Parcelable {


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

    protected Album(Parcel in) {
        id = in.readLong();
        name = in.readString();
        genre = in.readString();
        releaseDate = in.readString();
        stock = in.readInt();
        price = in.readDouble();
    }


    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };



    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Bindable
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        notifyPropertyChanged(BR.releaseDate);
    }

    @Bindable
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        notifyPropertyChanged(BR.stock);
    }

    @Bindable
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(genre);
        dest.writeString(releaseDate);
        dest.writeInt(stock);
        dest.writeDouble(price);
    }

}
