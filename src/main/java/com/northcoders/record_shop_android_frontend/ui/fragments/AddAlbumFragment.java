package com.northcoders.record_shop_android_frontend.ui.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.databinding.FragmentAddalbumBinding;
import com.northcoders.record_shop_android_frontend.ui.mainactivity.MainActivityViewModel;


public class AddAlbumFragment extends Fragment {

    private MainActivityViewModel viewModel;
    private FragmentAddalbumBinding binding;

    public AddAlbumFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_addalbum, container,false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}