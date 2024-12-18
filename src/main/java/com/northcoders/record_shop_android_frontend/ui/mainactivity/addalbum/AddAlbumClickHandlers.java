package com.northcoders.record_shop_android_frontend.ui.mainactivity.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivity;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;

import java.util.function.Predicate;

public class AddAlbumClickHandlers {

    Album album;
    Context context;
    MainActivityViewModel viewModel;

    public AddAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    Predicate<Album> checkAlbumProperties = albumToCheck -> {
        if(albumToCheck.getName() == null ||
                albumToCheck.getArtist().getName() == null ||
                albumToCheck.getArtist() == null ||
                albumToCheck.getGenre() == null ||
        albumToCheck.getReleaseDate() == null ||
                String.valueOf(albumToCheck.getPrice()) == null ||
                String.valueOf(albumToCheck.getStock())== null)
            {
            return true;
        }
        return false;
    };


    public void onSubmitAlbumButtonClick(View view){
        if(checkAlbumProperties.test(album)){
            // If there's empty fields when the user tries to submit an album,
            // Then the toast error message will appear
            Toast.makeText(context,"Fields cannot be empty",Toast.LENGTH_SHORT)
                    .show();
        }else{
            viewModel.createAlbum(album);

            Intent intent = new Intent(context, MainActivity.class);

            context.startActivity(intent);
        }
    }

    public void onGoBackButtonClick(View view){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);

    }

}
