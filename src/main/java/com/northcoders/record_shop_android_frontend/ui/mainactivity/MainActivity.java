package com.northcoders.record_shop_android_frontend.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.databinding.ActivityMainBinding;
import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recycler;
    private ArrayList<Album> albums;
    private ArrayList<Album> filteredAlbumsList;
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setHandler(new MainActivityClickHandler(this));

        getAllAlbums();

        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

    }

    private void filterList(String text){
        filteredAlbumsList = new ArrayList<>();

        for(Album album : albums){
            if(album.getName().toLowerCase().contains(text.toLowerCase())){
                filteredAlbumsList.add(album);
            }
        }
        if(filteredAlbumsList.isEmpty()){
            Toast.makeText(this, "No Albums Found", Toast.LENGTH_SHORT).show();
        }
        else{
            albumAdapter.setFilterList(filteredAlbumsList);
        }
    }

    private void getAllAlbums(){
        viewModel.getAllAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                albums = (ArrayList<Album>) albumsFromLiveData;
                displayInRecyclerView();
            }
        });
    }

    private void displayInRecyclerView(){

        recycler = binding.recycler;
        // Because this class implements the RecyclerViewInterface ->
        // "this" can be passed in for the interface parameter
        albumAdapter = new AlbumAdapter(albums,this,this);
        recycler.setAdapter(albumAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        albumAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, UpdateAlbumActivity.class);
        Album chosenAlbum = albums.get(position);
        // Check log to see if album correctly appears
        Log.i("Main Activity Choose Album",chosenAlbum.toString());

        // pass the chosen album to the next activity
        intent.putExtra(ALBUM_KEY,chosenAlbum);

        startActivity(intent);
    }
}