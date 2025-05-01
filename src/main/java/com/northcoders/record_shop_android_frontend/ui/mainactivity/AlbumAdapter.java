package com.northcoders.record_shop_android_frontend.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.databinding.AlbumItemLayoutBinding;
import com.northcoders.record_shop_android_frontend.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    List<Album> albumList;
    Context context;
    RecyclerViewInterface recyclerInterface;
    MainActivityViewModel viewModel;

    public AlbumAdapter(List<Album> albumList, Context context, RecyclerViewInterface recyclerInterface, MainActivityViewModel viewModel) {
        this.albumList = albumList;
        this.context = context;
        this.recyclerInterface = recyclerInterface;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.album_item_layout,
                parent,
                false);


        return new AlbumViewHolder(binding,recyclerInterface,viewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        //set the images for each album

        ImageView imageView = holder.binding.albumImage;
        Glide.with(imageView)
                .load(album.getUrl())
                .placeholder(R.drawable.vinyl)
                .fitCenter()
                .into(imageView);

        holder.binding.favouriteIcon
                .setImageResource(album.getFavourite() ?
                        R.drawable.ic_favourite_enabled : R.drawable.ic_favourites_foreground);

        holder.binding.setAlbum(album);
    }

    @Override
    public int getItemCount() {
        if(albumList == null){
        return 0;
        }
        else {
            return albumList.size();
        }
    }

    public void setFilterList(ArrayList<Album> filterList){
        this.albumList = filterList;
        notifyDataSetChanged();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{
        private final AlbumItemLayoutBinding binding;

        public AlbumViewHolder(AlbumItemLayoutBinding binding, RecyclerViewInterface recyclerInterface,MainActivityViewModel viewModel){
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                // Now to get the position of the chosen album from the recycler
                public void onClick(View v) {
                    if(recyclerInterface != null){
                        // Get position of the adapter
                        int position = getAdapterPosition();
                        // Set the position to the interface
                        if(position != RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(position);
                        }
                    }
                }
            });

            binding.favouriteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    binding.getAlbum().setFavourite(!binding.getAlbum().getFavourite());
                    Album album = new Album();
                    album.setId(binding.getAlbum().getId());
                    album.setFavourite(binding.getAlbum().getFavourite());

                    viewModel.updateAlbum(album);
                    Toast.makeText(itemView.getContext(), "Updating...", Toast.LENGTH_SHORT).show();
                    binding.favouriteIcon.setImageResource(binding.getAlbum().getFavourite() ? R.drawable.ic_favourite_enabled : R.drawable.ic_favourites_foreground);
                }
            });
        }
    }
}
