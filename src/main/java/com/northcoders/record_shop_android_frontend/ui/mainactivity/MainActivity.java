package com.northcoders.record_shop_android_frontend.ui.mainactivity;

import android.app.Application;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.model.AlbumRepository;

public class MainActivity extends AppCompatActivity {

    AlbumRepository albumRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Application application = new Application();
        albumRepository = new AlbumRepository(application);
        albumRepository.getMutableLiveData();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}