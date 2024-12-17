package com.northcoders.record_shop_android_frontend.service;

import com.northcoders.record_shop_android_frontend.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumApiService {
    @GET("albums")
    Call<List<Album>> getAllAlbums();

}
