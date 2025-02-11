package com.northcoders.record_shop_android_frontend.ui.addalbum;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.model.Artist;
import com.northcoders.record_shop_android_frontend.ui.fragments.HomeFragment;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;

import java.util.function.Predicate;

public class AddAlbumClickHandlers {

    Album album;
    Context context;
    FragmentActivity activity;
    MainActivityViewModel viewModel;

    public AddAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel,FragmentActivity activity) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
        this.activity = activity;
    }

    // Check if the inputs are empty
    Predicate<Album> checkAlbumProperties = albumToCheck -> {
        if(albumToCheck.getName() == null ||
                albumToCheck.getArtist().getName().isBlank() ||
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

        EditText artistTextBox = activity.findViewById(R.id.artistName);
        Artist thisArtist = new Artist();
        thisArtist.setName(artistTextBox.getText().toString());
        album.setArtist(thisArtist);

        if(checkAlbumProperties.test(album)){
            // If there's empty fields when the user tries to submit an album,
            // Then the toast error message will appear
            Toast.makeText(context,"Fields cannot be empty",Toast.LENGTH_SHORT)
                    .show();
        }else{
            viewModel.createAlbum(album);
            HomeFragment homeFragment = new HomeFragment();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayoutFragment, homeFragment)
                    .commit();

        }
    }

}



