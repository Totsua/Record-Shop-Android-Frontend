package com.northcoders.record_shop_android_frontend.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    public AlbumAdapter(List<Album> albumList, Context context, RecyclerViewInterface recyclerInterface) {
        this.albumList = albumList;
        this.context = context;
        this.recyclerInterface = recyclerInterface;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.album_item_layout,
                parent,
                false);


        return new AlbumViewHolder(binding,recyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        //set the images for each album
        //todo:
        // Save each image the first time Glide loads it, so it can pull it from memory
        ImageView imageView = holder.binding.albumImage;
        Glide.with(imageView)
                .load(album.getUrl())
                .placeholder(R.drawable.vinyl)
                .into(imageView);
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
        private AlbumItemLayoutBinding binding;

        public AlbumViewHolder(AlbumItemLayoutBinding binding, RecyclerViewInterface recyclerInterface){
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

        }
    }
}
