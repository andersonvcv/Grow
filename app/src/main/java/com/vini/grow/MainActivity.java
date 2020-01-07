package com.vini.grow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;
import com.vini.grow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Set context to singleton
        ResourcesProvider.getInstance().setApplicationContext(getApplicationContext());

        // Biding configuration
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        // Set to the same viewmodel everytime
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class); // make it persistent to system changes

        // dataBinding
        binding.setViewmodel(mainActivityViewModel);

        // add viewmodel as Lifecycle Observer
        getLifecycle().addObserver(mainActivityViewModel);

        // Show toast
        mainActivityViewModel.getToToast().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

    }
}
