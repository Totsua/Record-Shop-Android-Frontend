package com.northcoders.record_shop_android_frontend.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.record_shop_android_frontend.service.AlbumApiService;
import com.northcoders.record_shop_android_frontend.service.RetroFitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {
    private MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<List<Album>>();
    private Application application;

    public AlbumRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Album>> getMutableLiveData(){
        AlbumApiService albumApiService = RetroFitInstance.getService();
        Call<List<Album>> call = albumApiService.getAllAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> albumList = response.body();
                mutableLiveData.setValue(albumList);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.i("GET all request", t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public void createAlbum(Album album){
        AlbumApiService albumApiService = RetroFitInstance.getService();
        Call<Album> albumCall = albumApiService.postAlbum(album);
        albumCall.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(application.getApplicationContext(),
                        "The album has been posted",
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                                "The album cannot be posted",
                                Toast.LENGTH_SHORT)
                        .show();
                Log.e("POST failed",t.getMessage());
            }
        });
    }
}
