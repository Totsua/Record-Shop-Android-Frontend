package com.northcoders.record_shop_android_frontend.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;import android.widget.Toast;

import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.databinding.FragmentFirstBinding;
import com.northcoders.record_shop_android_frontend.model.Album;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.AlbumAdapter;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.RecyclerViewInterface;
import com.northcoders.record_shop_android_frontend.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment implements RecyclerViewInterface {

    private RecyclerView recycler;
    private ArrayList<Album> albums;
    private ArrayList<Album> filteredAlbumsList;
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel viewModel;
    private FragmentFirstBinding binding;
    private static final String ALBUM_KEY = "album";


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getAllAlbums();
        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
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
            Toast.makeText(this.getContext(), "No Albums Found", Toast.LENGTH_SHORT).show();
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
        albumAdapter = new AlbumAdapter(albums,this.getContext(),this);
        recycler.setAdapter(albumAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setHasFixedSize(true);
        albumAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this.getContext(), UpdateAlbumActivity.class);
        Album chosenAlbum = albums.get(position);
        // Check log to see if album correctly appears
        Log.i("Main Activity Choose Album",chosenAlbum.toString());

        if(filteredAlbumsList == null || filteredAlbumsList.isEmpty()){
            // pass the chosen album to the next activity
            intent.putExtra(ALBUM_KEY,chosenAlbum);
        }else{
            intent.putExtra(ALBUM_KEY,filteredAlbumsList.get(position));
        }

        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_first,container,false);
        View view = binding.getRoot();

    // Inflate the layout for this fragment
        return view;
    }

}