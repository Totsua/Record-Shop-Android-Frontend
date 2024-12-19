package com.northcoders.record_shop_android_frontend.ui.updatealbum;

import android.content.Context;

import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumClickHandler {
    Album album;
    MainActivityViewModel viewModel;
    long id;
    Context context;

    public UpdateAlbumClickHandler(Album album, MainActivityViewModel viewModel, Context context) {
        this.album = album;
        this.viewModel = viewModel;
        this.context = context;
    }






}
