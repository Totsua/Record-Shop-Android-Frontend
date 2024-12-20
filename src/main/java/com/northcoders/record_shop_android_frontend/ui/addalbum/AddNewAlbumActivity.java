package com.northcoders.record_shop_android_frontend.ui.addalbum;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.model.Artist;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;

public class AddNewAlbumActivity extends AppCompatActivity {


    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandlers handler;
    private MainActivityViewModel viewModel;
    private Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_album);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the artist name from the input
        // and create an artist with that name
        EditText editTextArtistName = findViewById(R.id.artistName);
        Artist thisArtist = new Artist();
        thisArtist.setName(editTextArtistName.getText().toString());

        album = new Album();
        album.setArtist(thisArtist);
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_new_album);

         viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handler = new AddAlbumClickHandlers(album,this,viewModel);

        binding.setAlbum(album);

        binding.setHandler(handler);
    }
}