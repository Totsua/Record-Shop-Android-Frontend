package com.northcoders.record_shop_android_frontend.ui.updatealbum;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;

import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.model.Artist;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivity;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;

import java.util.function.Predicate;

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

    Predicate<Album> isAlbumPropertiesValid = albumToCheck -> {
        if (albumToCheck.getName() == null ||
                albumToCheck.getArtist().getName().isBlank() ||
                albumToCheck.getArtist() == null ||
                albumToCheck.getGenre() == null ||
                albumToCheck.getReleaseDate() == null ||
                String.valueOf(albumToCheck.getPrice()) == null ||
                String.valueOf(albumToCheck.getStock()) == null)

        // Fields can be null(?) they can be empty but if empty they will become null?
        // Backend can ignore null values

        {
            return true;
        }
        return false;
    };

    public void onUpdateAlbumButtonClick(View view) {
        Album updatedAlbum = new Album();
        updatedAlbum.setId(album.getId());


        // Checks only see if a field is empty,
        // todo:
        //  check to see if the information is the same as it previously was
        //  if it is then don't include it in the response


        if (!album.getName().isBlank()) {
            updatedAlbum.setName(album.getName());
        }
        if (!album.getArtist().getName().isBlank()) {
            Artist artist = new Artist();
            artist.setId(album.getArtist().getId());
            artist.setName(album.getArtist().getName());
            updatedAlbum.setArtist(artist);
        }
        if (!album.getGenre().isBlank()) {
            updatedAlbum.setGenre(album.getGenre());
        }
        if (!album.getReleaseDate().isBlank()) {
            updatedAlbum.setReleaseDate(album.getReleaseDate());
        }
        // price and stock should be able to be 0
        // todo: change the price/stock input
        if (album.getPrice() > 0) {
            updatedAlbum.setPrice(album.getPrice());
        }
        if (album.getStock() > 0) {
            updatedAlbum.setStock(album.getStock());
        }


        viewModel.updateAlbum(updatedAlbum);
        Intent intent = new Intent(context, MainActivity.class);

        context.startActivity(intent);
    }


    public void onDeleteButtonClick(View view) {
        // Create an alert box that pops up when the button is pressed
        AlertDialog.Builder quit = new AlertDialog.Builder(this.context)
                .setTitle("Delete Album")
                .setMessage("Are you sure you want to delete \"" + album.getName() + "\"?")

                // Set what happens when "ok" is selected (delete the album)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        viewModel.deleteAlbum(album.getId());
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);

                    }
                })
                // Set what happens when "cancel" is pressed
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
        // Show the alert box
        quit.show();

    }


    public void onGoBackButtonClick(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
