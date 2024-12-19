package com.northcoders.record_shop_android_frontend.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.model.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private AlbumRepository albumRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.albumRepository = new AlbumRepository(application);
    }

    public MutableLiveData<List<Album>> getAllAlbums(){
        return albumRepository.getMutableLiveData();
    }

    public void createAlbum(Album album){
        albumRepository.createAlbum(album);
    }

    public void updateAlbum(long id,Album album){
        albumRepository.updateAlbum(album);
    }

    public void deleteAlbum(long id){
        albumRepository.deleteAlbum(id);
    }

}
