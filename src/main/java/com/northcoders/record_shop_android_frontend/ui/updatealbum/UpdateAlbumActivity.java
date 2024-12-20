package com.northcoders.record_shop_android_frontend.ui.updatealbum;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {
    ActivityUpdateAlbumBinding binding;
    UpdateAlbumClickHandler handler;
    Album album;
    MainActivityViewModel viewModel;
    private static final String ALBUM_KEY = "album";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_album);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        album = getIntent().getParcelableExtra(ALBUM_KEY, Album.class);
        // Check logs to see if album is correctly received
        Log.i("Update Album Chosen Album",album.toString());
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_update_album
        );
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        handler = new UpdateAlbumClickHandler(album, viewModel,this);
        binding.setHandler(handler);
        binding.setAlbum(album);

    }
}