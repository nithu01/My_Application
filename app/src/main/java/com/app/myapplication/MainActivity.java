package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    HomeViewModel homeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getoffer().observe(this, new Observer<OfferResponse>() {
            @Override
            public void onChanged(OfferResponse offerResponse) {

            }
        });
    }
}