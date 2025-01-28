package com.northcoders.record_shop_android_frontend.ui.mainactivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationBarView;
import com.northcoders.record_shop_android_frontend.R;
import com.northcoders.record_shop_android_frontend.ui.fragments.HomeFragment;
import com.northcoders.record_shop_android_frontend.ui.fragments.SecondFragment;
import com.northcoders.record_shop_android_frontend.ui.fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

NavigationBarView navigationBarView;
    HomeFragment homeFragment = new HomeFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBarView = findViewById(R.id.NavBar);
        navigationBarView.setOnItemSelectedListener(this);
        navigationBarView.setSelectedItemId(R.id.home);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Set the page to change to home(firstFragment) if "home" is selected
        if(item.getItemId() == R.id.home){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayoutFragment, homeFragment)
                    .commit();
            return true;
        }
        // Set the page to change to add(secondFragment) if "add" is selected
        if(item.getItemId() == R.id.add){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayoutFragment, secondFragment)
                    .commit();
            return true;
        }
        // Set the page to change to favourites(thirdFragment) if "favourites" is selected
        if(item.getItemId() == R.id.favourite){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayoutFragment, thirdFragment)
                    .commit();
            return true;
        }
        return false;
    }
}