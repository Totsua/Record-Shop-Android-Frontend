package com.northcoders.record_shop_android_frontend.service;

import com.northcoders.record_shop_android_frontend.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlbumApiService {
    @GET("albums")
    Call<List<Album>> getAllAlbums();

    @POST("albums/create")
    Call<Album> postAlbum(@Body Album album);

    @PATCH("albums/{id}")
    Call<Album> updateAlbum(@Path("id") long id,@Body Album album);

    @DELETE("albums/{id}")
    Call<Void> deleteAlbum(@Path("id") long id);

}
