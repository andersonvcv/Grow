package com.vini.grow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.vini.grow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    Spinner spinner;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Biding configuration
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class); // make it persistent to system changes
        binding.setViewmodel(mainActivityViewModel);
    }
}