package com.vini.grow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.vini.grow.util.ResourcesProvider;
import com.vini.grow.viewmodel.MainActivityViewModel;
import com.vini.grow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set context to singleton in order to get resources in the viewmodel
        ResourcesProvider.getInstance().setApplicationContext(getApplicationContext());

        // Set to the same viewmodel everytime
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // add viewmodel as Lifecycle Observer
        getLifecycle().addObserver(mainActivityViewModel);

        // dataBinding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(mainActivityViewModel);

        // Show toast
//        mainActivityViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
//                Toast.makeText(getApplicationContext(), users.get(1).getFisrtName(), Toast.LENGTH_LONG).show();
//            }
//        });

    }
}
