package com.vini.grow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.vini.grow.util.ResourcesProvider;
import com.vini.grow.viewmodel.MainActivityViewModel;
import com.vini.grow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

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
        mainActivityViewModel.getToToast().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

    }
}
